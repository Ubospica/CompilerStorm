package IR.Type;

import java.util.Objects;

public class PointerType extends Type {
	public Type baseType;
	public int dim;

	public static PointerType I8STAR = new PointerType(IntType.INT8);
	public static PointerType I32STAR = new PointerType(IntType.INT32);

	public PointerType(Type baseType) {
		this(baseType, 1);
	}
	public PointerType(Type baseType, int dim) {
		super(TypeEnum.POINTER);
		if (baseType.basicType == TypeEnum.POINTER) {
			this.baseType = ((PointerType) baseType).baseType;
			this.dim = ((PointerType) baseType).dim + dim;
		} else {
			this.baseType = baseType;
			this.dim = dim;
		}
	}


	@Override
	public int getSize() {
		if (size == -1) {
			size = 8;
		}
		return size;
	}

	public Type getDereference() {
		if (dim == 1) {
			return baseType;
		} else {
			return new PointerType(baseType, dim - 1);
		}
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o instanceof Type type && type.basicType == TypeEnum.NULL) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		PointerType that = (PointerType) o;
		return dim == that.dim && Objects.equals(baseType, that.baseType);
	}


	@Override
	public String toString() {
		var builder = new StringBuilder(baseType.toString());
		builder.append("*".repeat(dim));
		return builder.toString();
	}
}
