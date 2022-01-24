package IR.Value.Inst;

import IR.Type.Type;
import IR.Value.Use;
import IR.Value.Value;

/**
 * br label dest
 */
public class BrLabelInst extends Inst {

	public BrLabelInst(Value dest) {
		super(Type.VOID);
		addUse(dest);
	}
}
