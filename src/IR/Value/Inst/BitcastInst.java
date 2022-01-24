package IR.Value.Inst;

import IR.Type.Type;
import IR.Value.Use;
import IR.Value.Value;

/**
 * bitcast operandTy val to resultTy
 */
public class BitcastInst extends Inst {
	public BitcastInst(Type resultTy, Value val) {
		super(resultTy);
		addUse(val);
	}
}
