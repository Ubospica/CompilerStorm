package Builtin;

import AST.Scope.Type.ClassType;
import AST.Scope.Type.FuncType;
import Util.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// a static class
public final class BuiltinFunc {
    private static final Position methodPosition = new Position();

    public static ClassType arrayType = new ClassType("Array") {{
        var arraySizeFuncType = new FuncType("size", BuiltinType.intType, new ArrayList<>());
        addFunc("size", arraySizeFuncType, methodPosition);
    }};

    public static ClassType stringType = new ClassType("string") {{
        var lengthFuncType = new FuncType("length", BuiltinType.intType, new ArrayList<>());
        var substringFuncType = new FuncType("substring", BuiltinType.stringType, new ArrayList<>(Arrays.asList(BuiltinType.intType, BuiltinType.intType)));
        var parseIntFuncType = new FuncType("parseInt", BuiltinType.intType, new ArrayList<>());
        var ordFuncType = new FuncType("ord", BuiltinType.intType, new ArrayList<>(Arrays.asList(BuiltinType.intType)));

        addFunc("length", lengthFuncType, methodPosition);
        addFunc("substring", substringFuncType, methodPosition);
        addFunc("parseInt", parseIntFuncType, methodPosition);
        addFunc("ord", ordFuncType, methodPosition);
    }};

    public static HashMap<String, FuncType> function = new HashMap<> () {{
        put("print", new FuncType("print", BuiltinType.voidType, new ArrayList<>(Arrays.asList(BuiltinType.stringType))));
        put("println", new FuncType("println", BuiltinType.voidType, new ArrayList<>(Arrays.asList(BuiltinType.stringType))));
        put("printInt", new FuncType("printInt", BuiltinType.voidType, new ArrayList<>(Arrays.asList(BuiltinType.intType))));
        put("printlnInt", new FuncType("printlnInt", BuiltinType.voidType, new ArrayList<>(Arrays.asList(BuiltinType.intType))));
        put("getString", new FuncType("getString", BuiltinType.stringType, new ArrayList<>()));
        put("getInt", new FuncType("getInt", BuiltinType.intType, new ArrayList<>()));
        put("toString", new FuncType("toString", BuiltinType.stringType, new ArrayList<>(Arrays.asList(BuiltinType.intType))));
    }};
}
