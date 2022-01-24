package IR.Value.Constant;

import IR.Pass;
import IR.Type.Type;

/**
 * Use instanceof to check.
 */
public class NullConstant extends Constant{

	public NullConstant(Type type) {
		super(type);
	}

	public NullConstant() {
		this(Type.NULL);
	}

	public void accept(Pass pass) {
		pass.visit(this);
	}


}
