package Backend;


import IR.Pass;
import IR.Type.FuncType;
import IR.Type.PointerType;
import IR.Type.StructType;
import IR.Value.Constant.IntConstant;
import IR.Value.Constant.NullConstant;
import IR.Value.Constant.StrConstant;
import IR.Value.Constant.ZeroInitConstant;
import IR.Value.Global.BasicBlock;
import IR.Value.Global.Function;
import IR.Value.Global.Module;
import IR.Value.Global.Variable;
import IR.Value.Inst.*;
import IR.Value.Value;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class IRPrinter implements Pass {
	public PrintStream out;

	final private String indent = "    ";

	HashMap<String, AtomicInteger> globalNameId = new HashMap<>();
	HashMap<String, AtomicInteger> localNameId = new HashMap<>();
	boolean inFunc;


	public IRPrinter(PrintStream out) {
		this.out = out;
	}

	public void visit(Module it) {
		out.printf("source_filename = \"%s\"\n\n", it.filename);

		giveName(it);

		it.gConstant.forEach(x -> x.accept(this));
		if (!it.gConstant.isEmpty()) out.print("\n");

		it.gStruct.forEach((k, v) -> visit(v));
		if (!it.gStruct.isEmpty()) out.print("\n");

		it.gVar.forEach((k, v) -> v.accept(this));
		if (!it.gVar.isEmpty()) out.print("\n");

		it.gFunc.forEach((k, v) -> {
			if (v.blocks.isEmpty()) v.accept(this);
		});
		out.print("\n");

		inFunc = true;
		it.gFunc.forEach((k, v) -> {
			if (!v.blocks.isEmpty()) v.accept(this);
		});
	}

	void giveName(Module it) {
		it.gConstant.forEach(x -> getId(x, true));
		it.gStruct.forEach((k, v) -> getId(v, false));
		localNameId.clear();
		it.gVar.forEach((k, v) -> getId(v, true));
		it.gFunc.forEach((k, v) -> {
			getId(v, true);
			v.arg.forEach(x -> getId(x, false));
			v.blocks.forEach(x -> {
				getId(x, false);
				x.insts.forEach(x1 -> {
					if (!x1.useList.isEmpty()) {
						getId(x1, false);
					}
				});
			});
			localNameId.clear();
		});
	}

	// global
	public void visit(StructType it) {
		out.printf("%s = type { ", it.printId);
		for (int i = 0; i < it.varType.size(); ++i) {
			out.print(it.varType.get(i).toString());
			out.print(i == it.varType.size() - 1 ? "" : ", ");
		}
		out.print(" }\n");
	}

	public void visit(IntConstant it) {
		out.printf("%s %d", it.type.toString(), it.value);
	}

	// global constant
	public void visit(StrConstant it) {
		out.printf("%s = private unnamed_addr constant %s c\"%s\"\n", it.printId,
				((PointerType)it.type).getDereference().toString(), it.getIrString());
	}

	public void visit(NullConstant it) {
		out.printf("%s null", it.type.toString());
	}
	public void visit(ZeroInitConstant it) {
		out.printf("%s zeroinitializer", it.type.toString());
	}


	public void visit(Variable it) {
		if (!inFunc) {
			// global variable; initValue != null
			out.printf("%s = global ", it.printId);
			it.initValue.accept(this);
			out.print("\n");
		} else {
			// function argument
			out.printf("%s %s", it.type.toString(), it.printId);
		}
	}

	public void visit(Function it) {
		// function declaration
		if (it.blocks.isEmpty()) {
			var funcType = (FuncType)it.type;
			out.printf("declare %s %s(", funcType.returnType.toString(), it.printId);
			for (int i = 0; i < funcType.argType.size(); ++i) {
				out.print(funcType.argType.get(i).toString());
				out.print(i == funcType.argType.size() - 1 ? "" : ", ");
			}
			out.print(")\n");
		} else {
			// function definition
			out.printf("define %s %s(", ((FuncType) it.type).returnType.toString(), it.printId);
			for (int i = 0; i < it.arg.size(); ++i) {
				it.arg.get(i).accept(this);
				out.print(i == it.arg.size() - 1 ? "" : ", ");
			}
			out.print(") {\n");
			for (int i = 0; i < it.blocks.size(); ++i) {
				it.blocks.get(i).accept(this);
				out.print(i == it.blocks.size() - 1 ? "" : "\n");
			}
			out.print("}\n\n");
		}
	}

	public void visit(BasicBlock it) {
		out.printf("%s:\n", it.printId.substring(1));
		it.insts.forEach(x -> {
			out.print(indent);
			x.accept(this);
			out.print("\n");
		});
	}

	public void visit(Inst it) {
		if (!it.useList.isEmpty()) {
			out.printf("%s = ", it.printId);
		}
		if (it instanceof AllocaInst newIt) {
			out.printf("alloca %s", ((PointerType)newIt.type).getDereference().toString());
		} else if (it instanceof BinaryInst newIt) {
			out.printf("%s %s, %s", getOpStr(newIt.opType), getTypeValStr(newIt.getUse(0)),
					getValStr(newIt.getUse(1)));
		} else if (it instanceof BitcastInst newIt) {
			out.printf("bitcast %s to %s", getTypeValStr(newIt.getUse(0)), newIt.type.toString());
		} else if (it instanceof BrInst newIt) {
			out.printf("br %s, %s, %s", getTypeValStr(newIt.getUse(0)),
					getTypeValStr(newIt.getUse(1)), getTypeValStr(newIt.getUse(2)));
		} else if (it instanceof BrLabelInst newIt) {
			out.printf("br %s", getTypeValStr(newIt.getUse(0)));
		} else if (it instanceof CallInst newIt) {
			out.printf("call %s %s(", newIt.type.toString(), getValStr(newIt.getUse(0)));
			int cnt = 0;
			for (var x : newIt.operandList) {
				++cnt;
				if (cnt == 1) {
					continue;
				}
				out.print(getTypeValStr(x.val));
				out.print(cnt == newIt.operandList.size() ? "" : ", ");
			}
			out.print(")");
		} else if (it instanceof GEPInst newIt) {
			out.printf("getelementptr %s, %s, ",
					((PointerType)newIt.getUse(0).type).getDereference().toString(),
					getTypeValStr(newIt.getUse(0)));
			int cnt = 0;
			for (var x : newIt.operandList) {
				++cnt;
				if (cnt == 1) {
					continue;
				}
				out.print(getTypeValStr(x.val));
				out.print(cnt == newIt.operandList.size() ? "" : ", ");
			}
		} else if (it instanceof IcmpInst newIt) {
			out.printf("icmp %s %s, %s", getOpStr(newIt.opType), getTypeValStr(newIt.getUse(0)),
					getValStr(newIt.getUse(1)));
		} else if (it instanceof LoadInst newIt) {
			out.printf("load %s, %s",
					((PointerType)newIt.getUse(0).type).getDereference().toString(),
					getTypeValStr(newIt.getUse(0)));
		} else if (it instanceof RetInst newIt) {
			if (newIt.operandList.isEmpty()) {
				out.print("ret void");
			} else {
				out.printf("ret %s", getTypeValStr(newIt.getUse(0)));
			}
		} else if (it instanceof StoreInst newIt) {
			out.printf("store %s, %s", getTypeValStr(newIt.getUse(0)),
					getTypeValStr(newIt.getUse(1)));
		} else if (it instanceof UnreachableInst newIt) {
			out.print("unreachable");
		} else if (it instanceof PhiInst newIt) {
			out.printf("phi %s ", newIt.type.toString());
			int cnt = 0;
			for (var x : newIt.operandList) {
				++cnt;
				if ((cnt & 1) != 0) {
					out.printf("[ %s, ", getValStr(x.val));
				} else {
					out.printf("%s ]", getValStr(x.val));
					out.print(cnt == newIt.operandList.size() ? "" : ", ");
				}
			}
		}
	}

	public String getTypeValStr(Value val) {
		return val.type.toString() + " " + getValStr(val);
	}

	public String getValStr(Value val) {
		if (val instanceof IntConstant newVal) {
			return String.valueOf(newVal.value);
		} else if (val instanceof NullConstant) {
			return "null";
		} else if (val instanceof ZeroInitConstant) {
			return "zeroinitializer";
		} else {
			return val.printId;
		}
	}

	public String getOpStr(BinaryInst.OpType op) {
		return switch (op) {
			case ADD_NSW -> "add nsw";
			case SUB_NSW -> "sub nsw";
			case MUL_NSW -> "mul nsw";
			case SDIV -> "sdiv";
			case SREM -> "srem";
			case SHL -> "shl";
			case ASHR -> "ashr";
			case AND -> "and";
			case OR -> "or";
			case XOR -> "xor";
		};
	}

	public String getOpStr(IcmpInst.OpType op) {
		return switch (op) {
			case EQ -> "eq";
			case NE -> "ne";
			case UGT -> "ugt";
			case UGE -> "uge";
			case ULT -> "ult";
			case ULE -> "ule";
			case SGT -> "sgt";
			case SGE -> "sge";
			case SLT -> "slt";
			case SLE -> "sle";
		};
	}

	private int getNameId(String s, HashMap<String, AtomicInteger> nameId) {
		var id = nameId.get(s);
		if (id != null) {
			return id.getAndIncrement();
		} else {
			nameId.put(s, new AtomicInteger(1));
			return 0;
		}
	}

	private String getId(String val, boolean isGlobal) {
		var nameId = getNameId(val, isGlobal ? globalNameId : localNameId);
		return (isGlobal ? "@" : "%") + (val.isEmpty() ? "tmp." : val) +
				(nameId == 0 ? "" : String.valueOf(nameId));
	}

	private void getId(Value val, boolean isGlobal) {
		val.printId = getId(val.id, isGlobal);
	}

	private void getId(StructType val, boolean isGlobal) {
		val.printId = getId(val.id, isGlobal);
	}
}
