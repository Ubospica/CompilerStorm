package IR.Type;

public class Type {
	public final TypeEnum basicType;

	public static final Type VOID = new Type(TypeEnum.VOID);
	public static final Type LABEL = new Type(TypeEnum.LABEL);
	public static final Type NULL = new Type(TypeEnum.NULL);
	protected int size = -1;

	public Type(TypeEnum basicType) {
		this.basicType = basicType;
	}

	public boolean isVoid() {
		return basicType == TypeEnum.VOID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (this.basicType == TypeEnum.NULL && o instanceof PointerType) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) return false;
		Type type = (Type) o;
		return basicType == type.basicType;
	}

	public int getSize() {
		if (size == -1) {
			size = 0;
		}
		return size;
	}

	public String toString() {
		return switch (basicType) {
			case VOID -> "void";
			case LABEL -> "label";
			default -> null;
		};
	}
}
