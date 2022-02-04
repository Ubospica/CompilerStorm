package IR.Type;

import java.util.Objects;

public class IntType extends Type {
	public final int width;

	public static final IntType INT1 = new IntType(1);
	public static final IntType INT8 = new IntType(8);
	public static final IntType INT16 = new IntType(16);
	public static final IntType INT32 = new IntType(32);
	public static final IntType INT64 = new IntType(64);

	public IntType(int width) {
		super(TypeEnum.INT);
		this.width = width;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		IntType intType = (IntType) o;
		return width == intType.width;
	}

	public int getSize() {
		if (size == -1) {
//			size = (width == 1 ? 1 : width / 8);
			size = 4; // four byte alignment
		}
		return size;
	}

	@Override
	public String toString() {
		return "i" + width;
	}
}
