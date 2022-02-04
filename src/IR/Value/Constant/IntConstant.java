package IR.Value.Constant;

import IR.Pass;
import IR.Type.IntType;

public class IntConstant extends Constant {
	public int value;

	public static IntConstant TRUE = new IntConstant(1, 1);
	public static IntConstant FALSE = new IntConstant(0, 1);
	public static IntConstant BOOL_TRUE = new IntConstant(1, 8);
	public static IntConstant BOOL_FALSE = new IntConstant(0, 8);
	public static IntConstant ZERO = new IntConstant(0);
	public static IntConstant ONE = new IntConstant(1);
	public static IntConstant FOUR = new IntConstant(4); // sizeof(int32_t)
	public static IntConstant NEG_ONE = new IntConstant(-1);

	public IntConstant(int value, int width) {
		super(new IntType(width));
		this.value = value;
	}

	public IntConstant(int value) {
		this(value, 32);
	}


	public void accept(Pass pass) {
		pass.visit(this);
	}


}
