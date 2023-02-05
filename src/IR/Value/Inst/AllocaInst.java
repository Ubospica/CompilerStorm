package IR.Value.Inst;

import IR.Type.PointerType;
import IR.Type.Type;

public class AllocaInst extends Inst {
	public boolean isParam = false;

	public AllocaInst(Type operandTy) {
		super(new PointerType(operandTy));
	}

	public AllocaInst(Type operandTy, boolean isParam) {
		super(new PointerType(operandTy));
		this.isParam = isParam;
	}

}
