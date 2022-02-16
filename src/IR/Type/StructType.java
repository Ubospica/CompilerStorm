package IR.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class StructType extends Type {
	public String id;
	public String printId;
	public ArrayList<Type> varType = new ArrayList<>();
	public HashMap<String, Integer> fieldIdx = new HashMap<>();

	public StructType(String id) {
		super(TypeEnum.STRUCT);
		this.id = id;
	}

	@Override
	public int getSize() {
		if (size == -1) {
			size = 0;
			varType.forEach(x -> size += x.getSize());
		}
		return size;
	}

	public int getDelta(int cnt) {
		return cnt * 4;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		StructType that = (StructType) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public String toString() {
		return printId;
	}
}
