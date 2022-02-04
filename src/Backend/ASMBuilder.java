package Backend;

import ASM.ASMBlock;
import ASM.ASMFunc;
import ASM.ASMRoot;
import ASM.Inst.*;
import ASM.Operand.*;
import IR.Pass;
import IR.Type.PointerType;
import IR.Type.StructType;
import IR.Type.Type;
import IR.Value.Constant.*;
import IR.Value.Global.BasicBlock;
import IR.Value.Global.Function;
import IR.Value.Global.Module;
import IR.Value.Global.Variable;
import IR.Value.Inst.*;
import IR.Value.Inst.Inst;
import IR.Value.Value;
import org.antlr.v4.runtime.misc.Pair;

import java.util.HashMap;

public class ASMBuilder implements Pass {

	public ASMRoot root;

	private HashMap<Function, ASMFunc> funcMapping = new HashMap<>();
	private HashMap<BasicBlock, ASMBlock> blockMapping = new HashMap<>();
	private HashMap<Value, Reg> valueMapping = new HashMap<>();

	private ASMFunc currentFunc = null;
	private ASMBlock currentBlock = null;
	private ASMBlock funcEndBlock = null;


	public ASMBuilder(Module it) {
		root = new ASMRoot(it.filename);
		visit(it);
	}

	public void visit(Module it) {
		it.gFunc.forEach((k, v) -> {
			var asmFunc = new ASMFunc(v.id);
			funcMapping.put(v, asmFunc);
			root.funcs.add(asmFunc);
		});
		it.gFunc.forEach((k, v) -> v.accept(this));
	}

	public void visit(Function it) {
		var asmFunc = funcMapping.get(it);
		currentFunc = asmFunc;

		blockMapping.clear();
		valueMapping.clear();

		it.blocks.forEach(x -> {
			var asmBlock = new ASMBlock(it.id, x.id);
			blockMapping.put(x, asmBlock);
			asmFunc.blocks.add(asmBlock);
		});

		currentBlock = asmFunc.blocks.get(0);

		currentBlock.addInst(new Arith("addi", Reg.sp, Reg.sp, new Imm()));

		// save callee-save reg to tmp reg
		Reg.calleeSaveReg.forEach(x -> {
			var vReg = new VirtualReg();
			currentBlock.addInst(new Mv(vReg, x));
			asmFunc.savedRegs.add(new Pair<>(x, vReg));
		});
		var vRegRa = new VirtualReg();
		currentBlock.addInst(new Mv(vRegRa, Reg.ra));
		asmFunc.savedRegs.add(new Pair<>(Reg.ra, vRegRa));

		//s0 = frame pointer(?)

		// load arguments to tmp reg
		int offset = 0;
		for (int i = 0; i < it.argu.size(); ++i) {
			var vReg = new VirtualReg();
			if (i < 8) {
				currentBlock.addInst(new Mv(vReg, Reg.getRegById("a" + i)));
			} else {
				currentBlock.addInst(new Lw(vReg, Reg.sp, new Imm(offset)));
				offset += 4;
			}
			valueMapping.put(it.argu.get(i), vReg);
		}

		// function call epilogue
		funcEndBlock = new ASMBlock(it.id, "end");

		it.blocks.forEach(x -> x.accept(this));

		currentBlock = funcEndBlock;
		// restore saved reg
		asmFunc.savedRegs.forEach(x -> {
			currentBlock.addInst(new Mv(x.a, x.b));
		});
		currentBlock.addInst(new Ret());
	}

	// val could be:
	// 1. Variable (Mapped in function prologue)
	// 2. Inst
	// 3. Constant
	public Reg getValueReg(Value val) {
		var reg = valueMapping.get(val);
		if (reg == null) {
			if (val instanceof IntConstant newVal) {
				var valImm = new Imm(newVal.value);
				reg = new VirtualReg();
				if (valImm.isLow()) {
					currentBlock.addInst(new Li(reg, valImm));
				} else {
					currentBlock.addInst(new Lui(reg, valImm.high()));
					currentBlock.addInst(new Arith("add", reg, reg, valImm.low()));
				}
			} else if (val instanceof NullConstant newVal) {
				reg = Reg.zero;
			} else if (val instanceof StrConstant newVal) {
				reg = new VirtualReg();
				currentBlock.addInst(new Lla(reg, new Symbol(newVal.id)));
			} else if (val instanceof Inst) {
				reg = new VirtualReg();
			}
			valueMapping.put(val, reg);
		}
		return reg;
	}

	public void visit(BasicBlock it) {
		var asmBlock = blockMapping.get(it);
		currentBlock = asmBlock;
		it.insts.forEach(x -> x.accept(this));
	}

//	public enum OpType {
//		EQ, NE,
//		UGT, UGE, ULT, ULE,
//		SGT, SGE, SLT, SLE,
//	}

	public void visit(Inst it) {
		if (it instanceof AllocaInst newIt) {

		} else if (it instanceof BinaryInst newIt) {
			// can optimize: add imm
			var rs1 = getValueReg(newIt.getUse(0));
			var rs2 = getValueReg(newIt.getUse(1));
			var rd = getValueReg(newIt);
			String op = irOptoAsmStr(newIt.opType);
			currentBlock.addInst(new Arith(op, rd, rs1, rs2));
		} else if (it instanceof BitcastInst newIt) {
			currentBlock.addInst(new Mv(getValueReg(newIt), getValueReg(newIt.getUse(0))));
		} else if (it instanceof BrInst newIt) {
			// can optimize: blt bge beq...
			currentBlock.addInst(new Beqz(getValueReg(newIt.getUse(0)),
					blockMapping.get((BasicBlock) newIt.getUse(2))));
			currentBlock.addInst(new J(blockMapping.get((BasicBlock) newIt.getUse(1))));
		} else if (it instanceof BrLabelInst newIt) {
			currentBlock.addInst(new J(blockMapping.get((BasicBlock) newIt.getUse(0))));
		} else if (it instanceof CallInst newIt) {
			int cnt = 0;
			ASMFunc dest = null;
			for (var x : newIt.useList) {
				if (cnt == 0) {
					dest = funcMapping.get((Function) x.val);
				} else if (cnt <= 8){ // 1...8: copy to a[cnt-1]
					currentBlock.addInst(new Mv(Reg.getRegById("a" + (cnt - 1)), getValueReg(x.val)));
				} else {
					// offset = (cnt-9)*4
					// copy to -offset(sp)
					currentBlock.addInst(new Sw(getValueReg(x.val), Reg.sp, new Imm(-(cnt - 9) * 4)));
				}
				++cnt;
			}
		} else if (it instanceof GEPInst newIt) {
			int cnt = 0;
			Reg prevReg = null;
			Type prevType = null;
			for (var x : newIt.useList) {
				if (cnt == 0) {
					prevReg = getValueReg(x.val);
					prevType = x.val.type;
				} else {
					if (prevType instanceof PointerType) {
						prevType = ((PointerType) prevType).getDereference();
						var res = new VirtualReg();
						// except that imm is larger than imm size
						currentBlock.addInst(new Arith("mul", res, getValueReg(x.val), new Imm(prevType.getSize())));
						currentBlock.addInst(new Arith("add", res, prevReg, res));
						prevReg = res;
					} else if (prevType instanceof StructType sT) {
						// last element
						var res = new VirtualReg();
						currentBlock.addInst(new Arith("add", res, prevReg, new Imm(sT.getDelta(((IntConstant)x.val).value))));
						prevReg = res;
					}
				}
				++cnt;
			}
			valueMapping.put(it, prevReg);
		} else if (it instanceof IcmpInst newIt) {
			var rs1 = getValueReg(newIt.getUse(0));
			var rs2 = getValueReg(newIt.getUse(1));
			var rd = getValueReg(newIt);
			switch (newIt.opType) {
				case EQ -> {
					// xor  rd, rs1, rs2
					// seqz rd, rd
					currentBlock.addInst(new Arith("xor", rd, rs1, rs2));
					currentBlock.addInst(new Arith("seqz", rd, rd));
				}
				case NE -> {
					currentBlock.addInst(new Arith("xor", rd, rs1, rs2));
					currentBlock.addInst(new Arith("snez", rd, rd));
				}
				case UGT -> {
					currentBlock.addInst(new Arith("sltu", rd, rs2, rs1));
				}
				case UGE -> {
					currentBlock.addInst(new Arith("sltu", rd, rs1, rs2));
					currentBlock.addInst(new Arith("xor", rd, rd, new Imm(1)));
				}
				case ULT -> {
					currentBlock.addInst(new Arith("sltu", rd, rs1, rs2));
				}
				case ULE -> {
					currentBlock.addInst(new Arith("sltu", rd, rs2, rs1));
					currentBlock.addInst(new Arith("xor", rd, rd, new Imm(1)));
				}
				case SGT -> {
					currentBlock.addInst(new Arith("slt", rd, rs2, rs1));
				}
				case SGE -> {
					currentBlock.addInst(new Arith("slt", rd, rs1, rs2));
					currentBlock.addInst(new Arith("xor", rd, rd, new Imm(1)));
				}
				case SLT -> {
					currentBlock.addInst(new Arith("slt", rd, rs1, rs2));
				}
				case SLE -> {
					currentBlock.addInst(new Arith("slt", rd, rs2, rs1));
					currentBlock.addInst(new Arith("xor", rd, rd, new Imm(1)));
				}
			}
		} else if (it instanceof LoadInst newIt) {
			var ptr = newIt.getUse(0);
			var rd = getValueReg(newIt);
			if (ptr instanceof Variable var) {
				// load from global symbol
				var symbol = new Symbol(var.id);
				currentBlock.addInst(new Lw(rd, symbol));
			} else {
				var rs1 = getValueReg(newIt.getUse(0));
				currentBlock.addInst(new Lw(rd, rs1, new Imm(0)));
			}
		} else if (it instanceof StoreInst newIt) {
			var ptr = newIt.getUse(1);
			var rs2 = getValueReg(newIt.getUse(0)); // value
			if (ptr instanceof Variable var) {
				// load from global symbol
				var symbol = new Symbol(var.id);
				var reg = new VirtualReg();
				currentBlock.addInst(new Sw(rs2, reg, symbol));
			} else {
				var rs1 = getValueReg(newIt.getUse(1)); // ptr
				currentBlock.addInst(new Sw(rs2, rs1, new Imm(0)));
			}
		} else if (it instanceof PhiInst newIt) {
			var rd = getValueReg(newIt);
			// suppose: blocks in phi inst are visited
			// and jump inst is at the end of the block
			for (var x : newIt.args) {
				var block = blockMapping.get(x.b);
				var iter = block.insts.listIterator(block.insts.size());
				while(iter.hasPrevious()) {
					var pr = iter.previous();
					if (!(pr instanceof J || pr instanceof Beqz)) {
						iter.next();
						break;
					}
				}
				iter.add(new Mv(rd, getValueReg(x.a)));
			}
		} else if (it instanceof RetInst newIt) {
			if (!newIt.useList.isEmpty()) {
				currentBlock.addInst(new Mv(Reg.a0, getValueReg(newIt.getUse(0))));
			}
			currentBlock.addInst(new Ret());
		} else if (it instanceof UnreachableInst newIt) {
			// abnormal return
			currentBlock.addInst(new Ret());
		}
	}

	private String irOptoAsmStr(BinaryInst.OpType op) {
		return switch (op) {
			case ADD_NSW -> "add";
			case SUB_NSW -> "sub";
			case MUL_NSW -> "mul";
			case SDIV -> "div";
			case SREM -> "rem";
			case SHL -> "sll";
			case ASHR -> "sra";
			case AND -> "and";
			case OR -> "or";
			case XOR -> "xor";
		};
	}

}
