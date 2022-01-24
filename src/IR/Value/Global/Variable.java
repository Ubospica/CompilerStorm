package IR.Value.Global;

import IR.Pass;
import IR.Type.Type;
import IR.Value.Constant.Constant;
import IR.Value.Value;

/**
 * stands for Global Variables and Function Arguments
 */
public class Variable extends Value {
	public Constant initValue = null;
	public Variable(Type type, String id) {
		super(type);
		this.id = id;
	}
	public Variable(Type type, String id, Constant initValue) {
		super(type);
		this.id = id;
		this.initValue = initValue;
	}

	public void accept(Pass pass) {
		pass.visit(this);
	}

}
