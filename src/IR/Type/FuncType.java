package IR.Type;

import java.util.ArrayList;
import java.util.Arrays;

public class FuncType extends Type {
	public Type returnType = null;
	public ArrayList<Type> argType = new ArrayList<>();

	public FuncType() {
		super(TypeEnum.FUNCTION);
	}

	public FuncType(Type returnType, Type... argTypes) {
		super(TypeEnum.FUNCTION);
		this.returnType = returnType;
		argType = new ArrayList<>(Arrays.asList(argTypes));
	}

	// function pointer
	@Override
	public int getSize() {
		if (size == -1) {
			size = 8;
		}
		return size;
	}
}
