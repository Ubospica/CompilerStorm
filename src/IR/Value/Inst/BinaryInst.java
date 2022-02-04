package IR.Value.Inst;

import IR.Type.Type;
import IR.Value.Use;
import IR.Value.User;
import IR.Value.Value;

/**
 * add nsw operandTy op1, op2
 */
public class BinaryInst extends Inst {
	public enum OpType {
		// arithmetic
		ADD_NSW, SUB_NSW, MUL_NSW, SDIV, SREM,
		// bitwise
		// ASHR: sign extended
		SHL, ASHR, AND, OR, XOR,
	}
	public OpType opType;

	public BinaryInst(OpType opType, Value op1, Value op2) {
		super(op1.type);
		this.opType = opType;
		addUse(op1);
		addUse(op2);
	}
}
