package IR.Value.Inst;

import IR.Value.Value;

public class AssignInst extends Inst {
	public AssignInst(Value value) {
		super(value.type);
		addUse(value);
	}
}
