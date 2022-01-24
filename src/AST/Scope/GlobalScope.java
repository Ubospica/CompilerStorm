//package AST.Scope;
//
//import AST.Type.ArrayType;
//import AST.Type.ClassType;
//import AST.Type.FuncType;
//import AST.Type.Type;
//import AST.Type.TypeEnum;
//import Util.Error.SemanticError;
//import Util.Position;
//
//import java.util.HashMap;
//
//public class GlobalScope extends Scope{
//	protected HashMap<String, ClassType> types = new HashMap<>();
//	protected HashMap<String, FuncType> func = new HashMap<>();
//	public Scope parentScope;
//
//	public GlobalScope() {
//		super(null);
//	}
//
//	@Override
//	public void addVar(String name, Type t, Position pos) {
//		if (types.containsKey(name) || var.containsKey(name)) {
//			throw new SemanticError("Variable redefine", pos);
//		}
//		var.put(name, t);
//	}
//
//	public void addType(String name, ClassType t, Position pos) {
//		if (types.containsKey(name))
//			throw new SemanticError("Type name already defined " + name, pos);
//		types.put(name, t);
//	}
//
//	public ClassType getType(String name, Position pos) {
//		if (types.containsKey(name)) return types.get(name);
//		else throw new SemanticError("Type not found: " + name, pos);
//	}
//
//
//
//	public void addFunc(String name, FuncType type, Position pos) {
//		if (types.containsKey(name) || func.containsKey(name))
//			throw new SemanticError("Function name already defined " + name, pos);
//		func.put(name, type);
//	}
//
//	@Override
//	public Type getFuncType(String name, boolean lookUpon, Position pos) {
//		if (func.containsKey(name)) {
//			return func.get(name);
//		}
//		else if (lookUpon && parentScope != null) {
//			return parentScope.getFuncType(name, lookUpon, pos);
//		}
//		else {
//			throw new SemanticError("Function not found: " + name, pos);
//		}
//	}
//}
