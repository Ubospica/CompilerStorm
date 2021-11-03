package AST.Scope.Type;

import AST.TypeEnum;

import java.util.ArrayList;
import java.util.HashMap;

public class FuncType extends Type {
    public final String name;
    public final Type returnType;
    public final ArrayList<Type> param;

    public FuncType(String name, Type returnType, ArrayList<Type> param) {
        super(TypeEnum.FUNCTION);
        this.name = name;
        this.returnType = returnType;
        this.param = param;
    }

    // not used
    public boolean equals(Object rhs) {
        return super.equals(rhs) && name.equals(((FuncType)rhs).name);
    }
}
