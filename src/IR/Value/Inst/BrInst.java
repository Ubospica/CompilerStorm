package IR.Value.Inst;

import IR.Type.Type;
import IR.Value.Use;
import IR.Value.Value;

/**
 * br i1 cond, label iftrue, label iffalse
 */
public class BrInst extends Inst {

	public BrInst(Value cond, Value iftrue, Value iffalse) {
		super(Type.VOID);
		addUse(cond);
		addUse(iftrue);
		addUse(iffalse);
	}
}
