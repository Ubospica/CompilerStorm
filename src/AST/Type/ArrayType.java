package AST.Type;

public class ArrayType extends Type {
	public final Type baseType;
	public final int dim;

	public ArrayType(Type baseType, int dim) {
		super(TypeEnum.ARRAY);
		this.baseType = baseType;
		this.dim = dim;
	}

	public boolean equals(Object rhs) {
		return super.equals(rhs) && dim == ((ArrayType)rhs).dim;
	}
}
