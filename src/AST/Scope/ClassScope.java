//package AST.Scope;
//
//import AST.Scope.Type.Type;
//import AST.TypeEnum;
//import Util.Error.SemanticError;
//import Util.Position;
//
//import java.util.HashMap;
//
//public class ClassScope extends Scope{
//    protected HashMap<String, Type> func = new HashMap<>();
//    public Scope parentScope;
//
//    public ClassScope(Scope parentScope) {
//        super(parentScope);
//    }
//
//    public void addFunc(String name, Type type, Position pos) {
//        if (func.containsKey(name))
//            throw new SemanticError("Function name already defined " + name, pos);
//        func.put(name, type);
//    }
//}
