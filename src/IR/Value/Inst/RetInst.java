package IR.Value.Inst;

import IR.Type.Type;
import IR.Value.Use;
import IR.Value.Value;

/**
 * ret ty value
 * ret void
 */
public class RetInst extends Inst {

	// returns void
	public RetInst() {
		super(Type.VOID);
	}

	// value != null
	public RetInst(Value value) {
		super(Type.VOID);
		addUse(value);
	}

}