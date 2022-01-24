package IR.Value.Inst;

import IR.Type.IntType;
import IR.Type.Type;
import IR.Value.Use;
import IR.Value.Value;

public class IcmpInst extends Inst {
	public enum OpType {
		EQ, NE,
		UGT, UGE, ULT, ULE,
		SGT, SGE, SLT, SLE,
	}
	public OpType opType;
	public IcmpInst(OpType opType, Value op1, Value op2) {
		super(IntType.INT1);
		this.opType = opType;
		addUse(op1);
		addUse(op2);
	}
}
