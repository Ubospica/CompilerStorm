package AST.Type;

import AST.Definition.TypeNode;
import AST.Scope.Scope;

public class Type {
	public TypeEnum type;

	public Type(TypeEnum type) {
		this.type = type;
	}

	// output: basic class array
	// todo: bad design
	public static Scope globalScope;
	public static Type transNode(TypeNode type) {
		Type base;
		if (type.type == TypeEnum.CLASS) {
			base = globalScope.getType(type.identifier, type.pos);
//			base = new ClassType(type.identifier);
		} else {
			base = new Type(type.type);
		}
		if (type.dim != 0){
			return new ArrayType(base, type.dim);
		} else {
			return base;
		}
	}

	public boolean equals(Object rhs) {
		if (rhs == null || getClass() != rhs.getClass()) {
			return false;
		}
		return type == ((Type)rhs).type;
	}

	public boolean isInt() {
		return type == TypeEnum.INT;
	}
	public boolean isBool() {
		return type == TypeEnum.BOOL;
	}
	public boolean isString() {
		return type == TypeEnum.STRING;
	}
	public boolean isVoid() {
		return type == TypeEnum.VOID;
	}
	public boolean isNull() {
		return type == TypeEnum.NULL;
	}
	public boolean isArray() {
		return type == TypeEnum.ARRAY;
	}
	public boolean isFunc() {
		return type == TypeEnum.FUNCTION;
	}
	public boolean isClass() {
		return type == TypeEnum.CLASS;
	}
}
