package IR.Value.Inst;

import IR.Type.PointerType;
import IR.Type.Type;

public class AllocaInst extends Inst {

	public AllocaInst(Type operandTy) {
		super(new PointerType(operandTy));
	}

}
