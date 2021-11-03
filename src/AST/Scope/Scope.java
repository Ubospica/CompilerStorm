package AST.Scope;

import AST.Scope.Type.ArrayType;
import AST.Scope.Type.ClassType;
import AST.Scope.Type.FuncType;
import AST.Scope.Type.Type;
import AST.TypeEnum;
import Util.Error.InternalError;
import Util.Error.SemanticError;
import Util.Position;

import java.util.HashMap;

public class Scope {
    protected HashMap<String, Type> var = new HashMap<>();
    protected HashMap<String, ClassType> types = new HashMap<>();
    protected HashMap<String, FuncType> func = new HashMap<>();
    public Scope parentScope;

    public Scope(Scope parentScope) {
        this.parentScope = parentScope;
    }

    public Scope(Scope parentScope, HashMap<String, FuncType> builtinFunc) {
        this.parentScope = parentScope;
        func.putAll(builtinFunc);
    }


    public void addVar(String name, Type t, Position pos) {
        if (types.containsKey(name) || var.containsKey(name)) {
            throw new SemanticError("Variable redefine", pos);
        }
        var.put(name, t);
    }

    public Type getVarType(String name, boolean lookUpon, Position pos) {
        if (var.containsKey(name)) {
            return var.get(name);
        }
        else if (lookUpon && parentScope != null) {
            return parentScope.getVarType(name, lookUpon, pos);
        }
        else {
            throw new SemanticError("Variable not found: " + name, pos);
        }
    }


    public void addType(String name, ClassType t, Position pos) {
        // global scope only
        if (parentScope != null) {
            throw new InternalError("Find type at non-global scope", new Position());
        }
        if (types.containsKey(name)) {
            throw new SemanticError("Type name already defined " + name, pos);
        }
        types.put(name, t);
    }

    public ClassType getType(String name, Position pos) {
        // global scope only
        if (parentScope != null) {
            throw new InternalError("Find type at non-global scope", new Position());
        }
        if (types.containsKey(name)) {
            return types.get(name);
        }
        else {
            throw new SemanticError("Type not found: " + name, pos);
        }
    }

    public void checkType(Type type, Position pos) {
        // global scope only
        if (parentScope != null) {
            throw new InternalError("Find type at non-global scope", new Position());
        }
        if (type.type == TypeEnum.ARRAY) {
            checkType(((ArrayType)type).baseType, pos);
        } else if (type.type == TypeEnum.CLASS) {
            if (!types.containsKey(((ClassType)type).name)) {
                throw new SemanticError("Type not found: " + ((ClassType)type).name, pos);
            }
        }
    }

    public void addFunc(String name, FuncType type, Position pos) {
        if (types.containsKey(name) || func.containsKey(name))
            throw new SemanticError("Function name already defined " + name, pos);
        func.put(name, type);
    }

    public Type getFuncType(String name, boolean lookUpon, Position pos) {
        if (func.containsKey(name)) {
            return func.get(name);
        }
        else if (lookUpon && parentScope != null) {
            return parentScope.getFuncType(name, lookUpon, pos);
        }
        else {
            throw new SemanticError("Function not found: " + name, pos);
        }
    }
}
