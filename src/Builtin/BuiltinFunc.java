package Builtin;

import AST.Type.ClassType;
import AST.Type.FuncType;
import Util.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// a static class
public final class BuiltinFunc {
	private static final Position methodPosition = new Position();

	public static ClassType arrayType = new ClassType("Array") {{
		var arraySizeFuncType = new FuncType("size", BuiltinType.intType);
		addFunc("size", arraySizeFuncType, methodPosition);
	}};

	public static ClassType stringType = new ClassType("string") {{
		var lengthFuncType = new FuncType("length", BuiltinType.intType);
		var substringFuncType = new FuncType("substring", BuiltinType.stringType, BuiltinType.intType, BuiltinType.intType);
		var parseIntFuncType = new FuncType("parseInt", BuiltinType.intType);
		var ordFuncType = new FuncType("ord", BuiltinType.intType, BuiltinType.intType);

		addFunc("length", lengthFuncType, methodPosition);
		addFunc("substring", substringFuncType, methodPosition);
		addFunc("parseInt", parseIntFuncType, methodPosition);
		addFunc("ord", ordFuncType, methodPosition);
	}};

	public static HashMap<String, FuncType> function = new HashMap<> () {{
		put("print", new FuncType("print", BuiltinType.voidType, BuiltinType.stringType));
		put("println", new FuncType("println", BuiltinType.voidType, BuiltinType.stringType));
		put("printInt", new FuncType("printInt", BuiltinType.voidType, BuiltinType.intType));
		put("printlnInt", new FuncType("printlnInt", BuiltinType.voidType, BuiltinType.intType));
		put("getString", new FuncType("getString", BuiltinType.stringType));
		put("getInt", new FuncType("getInt", BuiltinType.intType));
		put("toString", new FuncType("toString", BuiltinType.stringType, BuiltinType.intType));
	}};
}
