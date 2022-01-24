package IR.Value.Constant;

import IR.Pass;
import IR.Type.Type;

/**
 * used to interpret "zeroinitializer"
 * Use instanceof to check.
 */
public class ZeroInitConstant extends Constant {

	public ZeroInitConstant(Type type) {
		super(type);
	}


	public void accept(Pass pass) {
		pass.visit(this);
	}

}
