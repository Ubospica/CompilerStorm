package Frontend;

import AST.ASTVisitor;
import AST.Definition.*;
import AST.Expression.*;
import AST.Expression.Atom.*;
import AST.Scope.Scope;
import AST.Type.*;
import AST.Statement.*;
import AST.Statement.ControlFlow.*;
import AST.Type.TypeEnum;
import Builtin.BuiltinFunc;
import Builtin.BuiltinType;
import Util.Error.InternalError;
import Util.Error.SemanticError;
import Util.Position;

import java.util.ArrayList;
import java.util.Stack;


// todo: replace new Type(TypeEnum) to BuiltinType
// todo: exception & document

public class SemanticChecker implements ASTVisitor {
	private Scope globalScope;
	private Scope curScope;

	private ClassType curClassType = null;
	private boolean inClass = false;

	private ArrayList<VarDefSubStmtNode> blockVar = null;
	private Stack<Type> returnTypes = new Stack<>();
	private boolean returned;

	private boolean inLoop = false;
	private boolean ignoreOuterScope = false;

	public SemanticChecker(Scope globalScope) {
		this.globalScope = globalScope;
		curScope = globalScope;

		// check main function
		FuncType ty = globalScope.getFuncType("main", false, new Position());
		if (!ty.equals(new FuncType("main", BuiltinType.intType, new ArrayList<>()))) {
			throw new SemanticError("main function defined incorrectly", new Position());
		}
	}

	@Override
	public void visit(ProgramNode it) {
		for (var i : it.body) {
			i.accept(this);
		}
	}


	@Override
	public void visit(FuncDefNode it) {
//		Scope funcScope = new Scope(curScope);
//		var pastScope = curScope;
//		curScope = funcScope;
//		startFunc = true;

		blockVar = it.paramList;
		var returnType = Type.transNode(it.returnType);
		returnTypes.push(returnType);

		boolean pastReturned = returned;
		returned = false;

		it.body.accept(this);

		if (!(curScope == globalScope && it.id.equals("main")) && !returnType.isVoid() && !returned) {
			throw new SemanticError("Function not returned: " + it.id, it.pos);
		}
		returnTypes.pop();

//		curScope = pastScope;
		returned = pastReturned;
	}

	@Override
	public void visit(ClassDefNode it) {
		var classScope = new Scope(globalScope);
		var pastScope = curScope;
		curScope = classScope;

		// no error
		ClassType classType = globalScope.getType(it.id, it.pos);
		inClass = true;
		curClassType = classType;

		// no init; don't worry
		for (var i : it.field) {
			i.accept(this);
		}
		for (var i : classType.func.entrySet()) {
			// no error
			curScope.addFunc(i.getKey(), i.getValue(), it.pos);
		}

		for (var i : it.constructor) {
			i.accept(this);
		}
		for (var i : it.method) {
			i.accept(this);
		}

		curScope = pastScope;
		inClass = false;
	}

	@Override
	public void visit(ConstructorDefNode it) {
//		Scope funcScope = new Scope(curScope);
//		var pastScope = curScope;
//		curScope = funcScope;
//		startFunc = true;

		blockVar = null;
		returnTypes.push(BuiltinType.voidType);

		boolean pastReturned = returned;
		returned = false;

		if (!it.id.equals(curClassType.name)) {
			throw new SemanticError("Constructor name mismatched: " + it.id, it.pos);
		}

		it.body.accept(this);

		returnTypes.pop();

		returned = pastReturned;
//		curScope = pastScope;
	}

	// not used : VarDefStmtNode does not exist on the tree
	@Override
	public void visit(VarDefStmtNode it) { }

	@Override
	public void visit(VarDefSubStmtNode it) {
		Type defType = Type.transNode(it.type);
		globalScope.checkType(defType, it.type.pos);

		if (it.init != null) {
			it.init.accept(this);
			if (it.init.type.isNull()) {
				if (!defType.isArray() && !defType.isClass()) {
					throw new SemanticError("VarDefSubStmtNode.init type error", it.pos);
				}
			} else if (!defType.equals(it.init.type)) {
				throw new SemanticError("VarDefSubStmtNode.init type error", it.pos);
			}
		}

		curScope.addVar(it.id, defType, it.pos);
	}

	@Override
	public void visit(ReturnStmtNode it) {
		if (returnTypes.empty()) {
			throw new SemanticError("return used out of a function", it.pos);
		}
		Type returnType;
		if (it.value == null) {
			returnType = new Type(TypeEnum.VOID);
		}else {
			it.value.accept(this);
			returnType = it.value.type;
		}

		if (returnTypes.peek().type == TypeEnum.LAMBDA_RETURN) {
			returnTypes.pop();
			returnTypes.push(returnType);
		} else {
			// todo: rewrite
			if (returnType.isNull()) {
				if (!returnTypes.peek().isArray() && !returnTypes.peek().isClass()) {
					throw new SemanticError("ReturnStmtNode.value type error", it.pos);
				}
			} else if (!returnTypes.peek().equals(returnType)) {
				throw new SemanticError("ReturnStmtNode.value type error", it.pos);
			}
		}
		returned = true;
	}

	@Override
	public void visit(BlockStmtNode it) {
		Scope pastScope = curScope;
		var blockScope = new Scope(ignoreOuterScope ? null : curScope);
		curScope = blockScope;

		if (blockVar != null) {
			for (var i : blockVar) {
				curScope.addVar(i.id, Type.transNode(i.type), i.pos);
			}
			blockVar = null;
		}

		it.body.forEach(x -> x.accept(this));

		curScope = pastScope;
	}

	@Override
	public void visit(ExprStmtNode it) {
		it.expr.accept(this);
	}

	private static boolean checkCondition(ExprNode cond) {
		return cond.type.isBool();
	}

	@Override
	public void visit(IfStmtNode it) {
		it.condition.accept(this);
		if (!checkCondition(it.condition)) {
			throw new SemanticError("IfStmtNode.condition type error", it.condition.pos);
		}
		it.thenStmt.accept(this);
		if (it.elseStmt != null) {
			it.elseStmt.accept(this);
		}
	}

	@Override
	public void visit(WhileStmtNode it) {
		// bug: cond not iterated
		it.cond.accept(this);
		if (!checkCondition(it.cond)) {
			throw new SemanticError("WhileStmtNode.cond type error", it.cond.pos);
		}
		boolean pastInloop = inLoop;
		inLoop = true;
		it.body.accept(this);
		inLoop = pastInloop;
	}

	@Override
	public void visit(ForStmtNode it) {
		if (it.init != null) {
			it.init.accept(this);
		}

		Scope pastScope = curScope;
		if (it.initDef != null) {
			var blockScope = new Scope(ignoreOuterScope ? null : curScope);
			curScope = blockScope;

			for (var i : it.initDef) {
				curScope.addVar(i.id, Type.transNode(i.type), i.pos);
			}
		}
		if (it.cond != null) {
			it.cond.accept(this);
			if (!checkCondition(it.cond)) {
				throw new SemanticError("ForStmtNode.cond type error", it.cond.pos);
			}
		}
		if (it.incr != null) {
			it.incr.accept(this);
		}

		boolean pastInLoop = inLoop;
		inLoop = true;

		it.body.accept(this);

		inLoop = pastInLoop;

		if (it.initDef != null) {
			curScope = pastScope;
		}
	}

	@Override
	public void visit(ControlStmtNode it) {
		if (!inLoop) {
			throw new SemanticError("break or continue used out of a loop", it.pos);
		}
	}

	@Override
	public void visit(EmptyStmtNode it) { }

	@Override
	public void visit(IntExprNode it) {
		it.type = new Type(TypeEnum.INT);
	}

	@Override
	public void visit(BoolExprNode it) {
		it.type = new Type(TypeEnum.BOOL);
	}

	@Override
	public void visit(StringExprNode it) {
		it.type = new Type(TypeEnum.STRING);
	}

	@Override
	public void visit(NullExprNode it) {
		it.type = new Type(TypeEnum.NULL);
	}


	private static boolean checkTypeIdentical(ExprNode left, ExprNode right) {
		return left.type.equals(right.type);
	}

	private boolean checkAssignable(Type left, Type right) {
		if (right.isNull()) {
			switch (left.type) {
				case ARRAY, CLASS -> {
					return true;
				}
				default -> {
					return false;
				}
			}
		} else {
			return left.equals(right);
		}
	}


	@Override
	public void visit(AssignExprNode it) {
		it.right.accept(this);
		it.left.accept(this);

		if (!it.left.assignable) {
			throw new SemanticError("Assign: BinaryExprNode.left is not assignable", it.left.pos);
		}

		if (it.right.type.isNull()) {
			switch (it.left.type.type) {
				case ARRAY, CLASS -> {}
				default -> throw new SemanticError("Assign: BinaryExprNode type error", it.pos);
			}
		} else {
			if (!checkTypeIdentical(it.left, it.right)) {
				throw new SemanticError("Assign: BinaryExprNode type error", it.pos);
			}
		}
	}

	@Override
	public void visit(BinaryExprNode it) {
		it.left.accept(this);
		it.right.accept(this);
		switch (it.op) {
//			case "," -> it.type = it.right.type;
			case "!=", "==" -> {
				switch (it.left.type.type) {
					case STRING, BOOL, INT -> { }
					case ARRAY, CLASS -> {
						if (!it.right.type.isNull()) {
							throw new SemanticError("BinaryExprNode type error", it.pos);
						}
					}
					case NULL -> {
						if (!it.right.type.isClass() && !it.right.type.isArray() && !it.right.type.isNull()) {
							throw new SemanticError("BinaryExprNode type error", it.pos);
						}
					}
					default -> throw new SemanticError("BinaryExprNode type error", it.pos);
				}
				if (!it.left.type.isArray() && !it.left.type.isClass() && !it.left.type.isNull() &&
						!checkTypeIdentical(it.left, it.right)) {
					throw new SemanticError("BinaryExprNode type error", it.pos);
				}
				it.type = new Type(TypeEnum.BOOL);
			}
			case "&&", "||" -> {
				if (!it.left.type.isBool() || !it.right.type.isBool()) {
					throw new SemanticError("BinaryExprNode type error", it.pos);
				}
				it.type = new Type(TypeEnum.BOOL);
			}
			case "<", "<=", ">", ">=" -> {
				if (!checkTypeIdentical(it.left, it.right)) {
					throw new SemanticError("BinaryExprNode type error", it.pos);
				}
				switch (it.left.type.type) {
					case STRING, INT -> { }
					default -> throw new SemanticError("BinaryExprNode type error", it.pos);
				}
				it.type = new Type(TypeEnum.BOOL);
			}
			case "+" -> {
				if (!checkTypeIdentical(it.left, it.right)) {
					throw new SemanticError("BinaryExprNode type error", it.pos);
				}
				switch (it.left.type.type) {
					case STRING, INT -> {
						it.type = new Type(it.left.type.type);
					}
					default -> throw new SemanticError("BinaryExprNode type error", it.pos);
				}
			}
			case "-", "*", "/", "%", "<<", ">>", "&", "|", "^" -> {
				if (!checkTypeIdentical(it.left, it.right)) {
					throw new SemanticError("BinaryExprNode type error", it.pos);
				}
				if (it.left.type.type == TypeEnum.INT) {
					it.type = new Type(it.left.type.type);
				} else {
					throw new SemanticError("BinaryExprNode type error", it.pos);
				}
			}
			default -> throw new InternalError("operator not found", it.pos);
		}
	}


	@Override
	public void visit(BinaryBoolExprNode it) {
		visit((BinaryExprNode) it);
	}

	@Override
	public void visit(PrefixExprNode it) {
		it.value.accept(this);
		if (it.op.equals("!")) {
			if (!it.value.type.isBool()) {
				throw new SemanticError("SuffixExprNode.value type error", it.value.pos);
			}
		} else {
			if (!it.value.type.isInt()) {
				throw new SemanticError("SuffixExprNode.value type error", it.value.pos);
			}
		}

		switch (it.op) {
			case "++", "--" -> {
				if (!it.value.assignable) {
					throw new SemanticError("PrefixExprNode.value is not assignable", it.value.pos);
				}
				it.assignable = true;
			}
		}

		it.type = it.value.type;
	}

	@Override
	public void visit(SuffixExprNode it) {
		it.value.accept(this);
		if (!it.value.type.isInt()) {
			throw new SemanticError("SuffixExprNode.value type error", it.value.pos);
		}

		if (!it.value.assignable) {
			throw new SemanticError("SuffixExprNode.value is not assignable", it.value.pos);
		}

		it.type = it.value.type;
	}

	// todo: lambda return null
	@Override
	public void visit(LambdaExprNode it) {
		blockVar = it.paramList;
		returnTypes.push(new Type(TypeEnum.LAMBDA_RETURN));

		boolean pastReturned = returned;
		returned = false;

		boolean pastInLoop = inLoop;
		inLoop = false;

		boolean pastIgnoreOuterScpoe = ignoreOuterScope;
		ignoreOuterScope = !it.isCapture;

		it.body.accept(this);

		ignoreOuterScope = pastIgnoreOuterScpoe;
		inLoop = pastInLoop;

		if (returnTypes.peek().type == TypeEnum.LAMBDA_RETURN) {
			returnTypes.peek().type = TypeEnum.VOID;
		}

		ArrayList<Type> param = new ArrayList<>();
		for (var i : it.paramList) {
//			if (i.init != null) {
//				throw new SemanticError("Function argument with init value: " + i.id, i.pos);
//			}
			Type paramType = Type.transNode(i.type);
			globalScope.checkType(paramType, i.type.pos);
			param.add(paramType);
		}
		it.type = new FuncType("lambda", returnTypes.peek(), param);

		returnTypes.pop();

		returned = pastReturned;
	}

	@Override
	public void visit(MemberExprNode it) {
		it.base.accept(this);
		switch (it.base.type.type) {
			case ARRAY -> {
				if (it.isFunc) {
					it.type = BuiltinFunc.arrayType.getFuncType(it.id, it.pos);
					it.isArray = true;
				} else {
					throw new SemanticError("MemberExprNode.base type error", it.base.pos);
				}
			}
			case STRING -> {
				if (it.isFunc) {
					it.type = BuiltinFunc.stringType.getFuncType(it.id, it.pos);
					it.isStr = true;
				} else {
					throw new SemanticError("MemberExprNode.base type error", it.base.pos);
				}
			}
			case CLASS -> {
				var classType = (ClassType)it.base.type;
				if (it.isFunc) {
					it.type = classType.getFuncType(it.id, it.pos);
				} else {
					it.type = classType.getVarType(it.id, it.pos);
					it.assignable = true;
				}
			}
			default -> throw new SemanticError("MemberExprNode.base type error", it.base.pos);
		}
	}

	@Override
	public void visit(FuncCallExprNode it) {
		it.func.accept(this);
		if (!it.func.type.isFunc()) {
			throw new SemanticError("FuncCallExprNode.func type error", it.func.pos);
		}
		var funcType = (FuncType)it.func.type;
		if (it.argList.size() != funcType.param.size()) {
			throw new SemanticError("FuncCallExprNode.param length does not match", it.func.pos);
		}
		for (int i = 0; i < it.argList.size(); ++i) {
			it.argList.get(i).accept(this);
			if (!checkAssignable(funcType.param.get(i), it.argList.get(i).type)) {
				throw new SemanticError("FuncCallExprNode.param No." + (i + 1) + " does not match",
						it.argList.get(i).pos);
			}
		}
		it.type = funcType.returnType;
	}

	@Override
	public void visit(SubscriptExprNode it) {
		it.base.accept(this);
		if (!it.base.type.isArray()) {
			throw new SemanticError("SubscriptExprNode.base type error", it.base.pos);
		}
		it.index.accept(this);
		if (!it.index.type.isInt()) {
			throw new SemanticError("SubscriptExprNode.index type error", it.base.pos);
		}
		var baseType = (ArrayType)it.base.type;
		if (baseType.dim == 1) {
			it.type = baseType.baseType;
		} else {
			it.type = new ArrayType(baseType.baseType, baseType.dim - 1);
		}
		it.assignable = true;
	}

	@Override
	public void visit(NewExprNode it) {
		it.type = Type.transNode(it.newType);
		globalScope.checkType(it.type, it.pos);
		for (var i : it.sizes) {
			i.accept(this);
			if (!i.type.isInt()) {
				throw new SemanticError("Size of new array is not an int", i.pos);
			}
		}
	}

	@Override
	public void visit(ThisExprNode it) {
		if (!inClass) {
			throw new SemanticError("this used out of class", it.pos);
		}
		it.type = curClassType;
	}

	@Override
	public void visit(VarExprNode it) {
		if (it.isFunc) {
			it.type = curScope.getFuncType(it.id, true, it.pos);
		} else {
			it.type = curScope.getVarType(it.id, true, it.pos);
			it.assignable = true;
		}
	}

	@Override
	public void visit(TypeNode it) { }
}
