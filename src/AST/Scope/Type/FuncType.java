package AST.Scope.Type;

import AST.TypeEnum;
import Util.Error.InternalError;
import Util.Position;

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

    // returntype & param should be inited
    public boolean equals(Object rhs) {
        if (!super.equals(rhs)) {
            return false;
        }
        var another = (FuncType)rhs;
        if (returnType == null || param == null) {
            throw new InternalError("FuncType with null field is compared", new Position());
        }
        return name.equals(another.name) && returnType.equals(another.returnType) && param.equals(another.param);
    }
}
