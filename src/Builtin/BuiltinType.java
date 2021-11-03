package Builtin;

import AST.Scope.Type.Type;
import AST.TypeEnum;

public final class BuiltinType {
    public static Type intType = new Type(TypeEnum.INT);
    public static Type stringType = new Type(TypeEnum.STRING);
    public static Type boolType = new Type(TypeEnum.BOOL);
    public static Type nullType = new Type(TypeEnum.NULL);
    public static Type voidType = new Type(TypeEnum.VOID);
}