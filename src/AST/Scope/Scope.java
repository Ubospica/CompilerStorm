package AST.Scope;

import java.util.HashMap;

import org.antlr.v4.runtime.misc.Pair;

import AST.Type.ArrayType;
import AST.Type.ClassType;
import AST.Type.FuncType;
import AST.Type.Type;
import AST.Type.TypeEnum;
import IR.Value.Value;
import Util.Position;
import Util.Error.InternalError;
import Util.Error.SemanticError;

public class Scope {
	public HashMap<String, Type> var = new HashMap<>();
	public HashMap<String, ClassType> types = new HashMap<>();
	public HashMap<String, FuncType> func = new HashMap<>();

	public HashMap<String, Value> entities = new HashMap<>();
	public Scope parentScope;

	public boolean isClassScope = false;

	public Scope(Scope parentScope) {
		this.parentScope = parentScope;
	}

	public Scope(Scope parentScope, boolean isClassScope) {
		this.parentScope = parentScope;
		this.isClassScope = isClassScope;
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

	public void addEntity(String name, Value val) {
		entities.put(name, val);
	}

	public Value getEntity(String name, boolean lookUpon) {
		if (entities.containsKey(name)) {
			return entities.get(name);
		}
		else if (lookUpon && parentScope != null) {
			return parentScope.getEntity(name, lookUpon);
		}
		else {
			return null;
		}
	}

	// a solution to fix a bug: see IRBuilder.java:915
	public Pair<Value, Boolean> getEntityInClass(String name, boolean lookUpon) {
		if (entities.containsKey(name)) {
			return new Pair<>(entities.get(name), isClassScope);
		}
		else if (lookUpon && parentScope != null) {
			var result = parentScope.getEntityInClass(name, lookUpon);
			return new Pair<>(result.a, result.b | isClassScope);
		}
		else {
			return new Pair<>(null, false);
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

	// check if the type exists
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

	public FuncType getFuncType(String name, boolean lookUpon, Position pos) {
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
