package Backend;

import ASM.ASMBlock;
import ASM.ASMFunc;
import ASM.ASMRoot;
import ASM.Inst.Mv;
import ASM.Operand.Reg;
import ASM.Operand.VirtualReg;
import IR.Pass;
import IR.Value.Constant.IntConstant;
import IR.Value.Constant.NullConstant;
import IR.Value.Constant.StrConstant;
import IR.Value.Constant.ZeroInitConstant;
import IR.Value.Global.BasicBlock;
import IR.Value.Global.Function;
import IR.Value.Global.Module;
import IR.Value.Global.Variable;
import IR.Value.Inst.Inst;
import IR.Value.Value;

import java.util.HashMap;

public class ASMBuilder implements Pass {

	public ASMRoot root;

	private HashMap<Function, ASMFunc> funcMapping = new HashMap<>();
	private HashMap<BasicBlock, ASMBlock> blockMapping = new HashMap<>();
	private HashMap<Value, Reg> regMapping = new HashMap<>();

	private ASMFunc currentFunc = null;
	private ASMBlock currentBlock = null;


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

		it.blocks.forEach(x -> {
			var asmBlock = new ASMBlock(it.id, x.id);
			blockMapping.put(x, asmBlock);
			asmFunc.blocks.add(asmBlock);
		});

		currentBlock = asmFunc.blocks.get(0);

		// save callee-save reg to tmp reg
		Reg.calleeSaveReg.forEach(x -> {
			currentBlock.addInst(new Mv(new VirtualReg(), x));
		});





		it.blocks.forEach(x -> x.accept(this));
	}

	public void visit(BasicBlock it) {
		var asmBlock = blockMapping.get(it);

	}

	public void visit(Inst it) {

	}


	public void visit(Variable it) { }
	public void visit(IntConstant it) { }
	public void visit(NullConstant it) { }
	public void visit(StrConstant it) { }
	public void visit(ZeroInitConstant it) { }
}
