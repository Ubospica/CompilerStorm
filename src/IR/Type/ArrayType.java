package IR.Type;

import java.util.ArrayList;

public class ArrayType extends Type {
	public Type baseType;
	public ArrayList<Integer> dims = new ArrayList<>();

	public ArrayType(Type baseType) {
		super(TypeEnum.ARRAY);
		this.baseType = baseType;
	}

	public ArrayType(Type baseType, int dim) {
		super(TypeEnum.ARRAY);
		this.baseType = baseType;
		dims.add(dim);
	}


	@Override
	public String toString() {
		if (dims.size() == 0) {
			return baseType.toString();
		} else {
			var newArrayType = new ArrayType(baseType);
			var newDims = new ArrayList<>(dims);
			newDims.remove(0);
			newArrayType.dims = newDims;
			return "[" + dims.get(0) + " x " + newArrayType + "]";
		}
	}
}
