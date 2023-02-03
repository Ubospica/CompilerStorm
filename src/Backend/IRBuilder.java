package Backend;

import AST.ASTVisitor;
import AST.Definition.*;
import AST.Expression.*;
import AST.Expression.Atom.*;
import AST.Scope.Scope;
import AST.Statement.*;
import AST.Statement.ControlFlow.*;
import IR.Type.*;
import IR.Value.Constant.IntConstant;
import IR.Value.Constant.NullConstant;
import IR.Value.Constant.StrConstant;
import IR.Value.Constant.ZeroInitConstant;
import IR.Value.Global.BasicBlock;
import IR.Value.Global.Function;
import IR.Value.Global.Module;
import IR.Value.Global.Variable;
import IR.Value.Inst.*;
import IR.Value.Value;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static Builtin.BuiltinLLVM.builtinFunc;
import static IR.Type.PointerType.I32STAR;
import static IR.Type.PointerType.I8STAR;

// todo:
// 1. add @ or % to var name
// 3. bool -> i8
// 4. annotation: variable naming, block naming
// 5. handle builtin
public class IRBuilder implements ASTVisitor {

	private static final int ptrSize = 8;
	private static final String constructorSuffix = "..constructor";

	Module topModule;

	ProgramNode astRoot;

	Function initFunc;

	// Scope: used for mapping variable id -> Value
	Scope currentScope;
	BasicBlock currentBlock = null;
	// (var def is) in global := (currentFunction == null)
	Function currentFunction = null;
	// (function is) class function := (currentStruct != null)
	StructType currentStruct = null;

	// prepare for break & continue
	BasicBlock currentBreakDest = null, currentContDest = null;

	public IRBuilder(String filename, ProgramNode astRoot) {
		this.astRoot = astRoot;
		topModule = new Module(filename);
		currentScope = new Scope(null);
	}

	public Module work() {
		visit(astRoot);
		return topModule;
	}

	@Override
	public void visit(ProgramNode it) {
		// put name of struct into topModule
		// so they can be found by identifier
		initBuiltin();

		// init function
		initFunc = new Function(new FuncType(Type.VOID), "var.init");
		initFunc.blocks.add(new BasicBlock("func.entry"));
		topModule.gFunc.put(initFunc.id, initFunc);

		it.body.forEach(x -> {
			if (x instanceof ClassDefNode newX) {
				topModule.gStruct.put(newX.id, new StructType(newX.id));
			}
		});
		// put fields of struct into topModule
		// so the fields can be found by identifier
		it.body.forEach(x -> {
			if (x instanceof ClassDefNode newX) {
				var structType = topModule.gStruct.get(newX.id);
				for (int i = 0; i < newX.field.size(); ++i) {
					var tmp = newX.field.get(i);
					tmp.type.accept(this);
					structType.varType.add(tmp.type.irType);
					structType.fieldIdx.put(tmp.id, i);
				}
			}
		});
		// put the functions into topModule
		it.body.forEach(x -> {
			if (x instanceof FuncDefNode newX) {
				addFunc(newX, null);
			}
			else if (x instanceof ClassDefNode newX) {
				var currentStruct = topModule.gStruct.get(newX.id);
				if (!newX.constructor.isEmpty()) {
					String id = newX.id + constructorSuffix;
					topModule.gFunc.put(id, new Function(new FuncType(Type.VOID, new PointerType(currentStruct)), id));
				}
				newX.method.forEach(y -> {
					addFunc(y, currentStruct);
				});
			}
		});

		it.body.forEach(x -> x.accept(this));

		// init function return; main call init function
		currentBlock = initFunc.blocks.getLast();
		addInst(new RetInst());
		topModule.gFunc.get("main").blocks.getFirst().insts.addFirst(new CallInst(initFunc));


		firstPass(topModule);
//		new IRPrinter(null).giveName(topModule);
	}

	// function id, return type, argument type
	void addFunc(FuncDefNode it, StructType currentStruct) {
		String id = (currentStruct == null ? "" : currentStruct.id + ".") + it.id;

		it.returnType.accept(this);
		var funcType = new FuncType(it.returnType.irType);

		if (currentStruct != null) {
			funcType.argType.add(new PointerType(currentStruct));
		}

		it.paramList.forEach(x -> {
			x.type.accept(this);
			funcType.argType.add(x.type.irType);
		});
		topModule.gFunc.put(id, new Function(funcType, id));
	}

	void firstPass(Module it) {
		// define var.init function
		// define internal void @__cxx_global_var_init() section ".text.startup" {}

		// give string name
		int cnt = 0;
		for (var x : it.gConstant) {
			x.id = x.id + (cnt++);
		}

		// erase useless inst
		it.gFunc.forEach((k, v) -> {
			v.blocks.forEach(x -> {
				x.insts.removeIf(x1 -> (x1.type.basicType != TypeEnum.VOID &&
						!(x1 instanceof CallInst) && x1.useList.isEmpty()));
			});
		});
	}

	void initBuiltin() {
		builtinFunc.forEach(x -> topModule.gFunc.put(x.id, x));
	}

	@Override
	public void visit(FuncDefNode it) {

		var funcName = currentStruct == null ? it.id : currentStruct.id + "." + it.id;
		var func = topModule.gFunc.get(funcName);
		var ty = (FuncType)func.type;

		currentFunction = func;

		// init block: initialize parameter
		currentBlock = new BasicBlock("func.entry");
		func.blocks.add(currentBlock);

		currentScope = new Scope(currentScope);

		// struct parameter "this"
		if (currentStruct != null) {
			var ptrTy = ty.argType.get(0);
			var variable = new Variable(ptrTy, "this");
			func.arg.add(variable);
			var allocaInst = addInst(new AllocaInst(ptrTy), "this.ptr");
			addInst(new StoreInst(variable, allocaInst));
			currentScope.addEntity("this", allocaInst);
		}

		it.paramList.forEach(x -> {
			var variable = new Variable(x.type.irType, x.id);
			func.arg.add(variable);
			var allocaInst = addInst(new AllocaInst(x.type.irType), x.id + ".ptr");
			addInst(new StoreInst(variable, allocaInst));
			currentScope.addEntity(x.id, allocaInst);
		});

		it.body.body.forEach(x -> x.accept(this));

		// function returns if there is no explicit return instruction
		if (currentBlock.insts.isEmpty() ||
				!(currentBlock.insts.get(currentBlock.insts.size() - 1) instanceof RetInst)) {
			addInst(switch (ty.returnType.basicType) {
				case VOID -> new RetInst();
				case INT -> new RetInst(new IntConstant(0, ((IntType)ty.returnType).width));
				default -> new UnreachableInst();
			});
		}

		currentScope = currentScope.parentScope;
		currentFunction = null;
	}

	// todo: function finding
	@Override
	public void visit(ClassDefNode it) {
		currentStruct = topModule.gStruct.get(it.id);
		currentScope = new Scope(currentScope, true);
		it.constructor.forEach(x -> x.accept(this));
		it.method.forEach(x -> x.accept(this));
		currentScope = currentScope.parentScope;
		currentStruct = null;
	}

	@Override
	public void visit(ConstructorDefNode it) {
		var func = topModule.gFunc.get(currentStruct.id + constructorSuffix);
		var ty = (FuncType)func.type;

		// init block: initialize parameter
		currentBlock = new BasicBlock("func.entry");
		func.blocks.add(currentBlock);

		currentScope = new Scope(currentScope);

		// struct parameter "this"
		if (currentStruct != null) {
			var ptrTy = ty.argType.get(0);
			var variable = new Variable(ptrTy, "this");
			func.arg.add(variable);
			var allocaInst = addInst(new AllocaInst(ptrTy), "this.ptr");
			addInst(new StoreInst(variable, allocaInst));
			currentScope.addEntity("this", allocaInst);
		}

		currentFunction = func;
		it.body.body.forEach(x -> x.accept(this));

		// function returns if there is no explicit return instruction
		if (currentBlock.insts.size() == 0 ||
			!(currentBlock.insts.get(currentBlock.insts.size() - 1) instanceof RetInst)) {
			addInst(new RetInst());
		}

		currentFunction = null;
		currentScope = currentScope.parentScope;
	}

	@Override
	public void visit(VarDefStmtNode it) {
		it.varList.forEach(x -> x.accept(this));
	}

	@Override
	public void visit(VarDefSubStmtNode it) {
		it.type.accept(this);
		if (it.isGlobal) {
			var ptr = new Variable(new PointerType(it.type.irType), it.id, new ZeroInitConstant(it.type.irType));
			topModule.gVar.put(it.id, ptr);
			currentScope.addEntity(it.id, ptr);
			currentFunction = initFunc;
			currentBlock = initFunc.blocks.getLast();
		} else {
			var ptr = addInst(new AllocaInst(it.type.irType), it.id);
			currentScope.addEntity(it.id, ptr);
		}
		// init
		if (it.init != null) {
			var assignNode = new AssignExprNode(new VarExprNode(it.id, null), it.init, null);
			visit(assignNode);
		}
	}

	@Override
	public void visit(ReturnStmtNode it) {
		if (it.value != null) {
			it.value.accept(this);
			if (it.value.irValue instanceof NullConstant) {
				it.value.irValue.type = ((FuncType)currentFunction.type).returnType;
			}
			addInst(new RetInst(it.value.irValue));
		} else {
			addInst(new RetInst());
		}
	}

	/**
	 * simple block:
	 * not start of a function
	 * starts a new scope
	 */
	@Override
	public void visit(BlockStmtNode it) {
		currentScope = new Scope(currentScope);

		it.body.forEach(x -> x.accept(this));

		currentScope = currentScope.parentScope;
	}

	@Override
	public void visit(ExprStmtNode it) {
		it.expr.accept(this);
	}

	private void checkConditionBool(ExprNode cond) {
		// translate int to bool
		if (!cond.irValue.type.equals(IntType.INT1)) {
			var oldVal = cond.irValue;
			var width = new IntConstant(0, ((IntType)oldVal.type).width);
			var icmp = addInst(new IcmpInst(IcmpInst.OpType.NE, oldVal,
					width), "to.bool");
			cond.irValue = icmp;
		}
	}

	@Override
	public void visit(IfStmtNode it) {
		if (it.elseStmt != null) {
			var ifThen = new BasicBlock("if.then");
			var ifElse = new BasicBlock("if.else");
			var ifEnd = new BasicBlock("if.end");

			it.condition.accept(this);
			checkConditionBool(it.condition);
			addInst(new BrInst(it.condition.irValue, ifThen, ifElse));

			currentFunction.blocks.add(ifThen);
			currentBlock = ifThen;
			it.thenStmt.accept(this);
			addInst(new BrLabelInst(ifEnd));

			currentFunction.blocks.add(ifElse);
			currentBlock = ifElse;
			it.elseStmt.accept(this);
			addInst(new BrLabelInst(ifEnd));

			currentFunction.blocks.add(ifEnd);
			currentBlock = ifEnd;
		} else {
			var ifThen = new BasicBlock("if.then");
			var ifEnd = new BasicBlock("if.end");

			it.condition.accept(this);
			checkConditionBool(it.condition);
			addInst(new BrInst(it.condition.irValue, ifThen, ifEnd));

			currentFunction.blocks.add(ifThen);
			currentBlock = ifThen;
			it.thenStmt.accept(this);
			addInst(new BrLabelInst(ifEnd));

			currentFunction.blocks.add(ifEnd);
			currentBlock = ifEnd;
		}
	}

	@Override
	public void visit(WhileStmtNode it) {
		var whileCond = new BasicBlock("while.cond");
		var whileBody = new BasicBlock("while.body");
		var whileEnd = new BasicBlock("while.end");

		addInst(new BrLabelInst(whileCond));

		currentFunction.blocks.add(whileCond);
		currentBlock = whileCond;
		it.cond.accept(this);
		checkConditionBool(it.cond);
		addInst(new BrInst(it.cond.irValue, whileBody, whileEnd));


		currentFunction.blocks.add(whileBody);
		currentBlock = whileBody;

		// prepare for while and continue
		// break: jump to whileEnd
		// continue: jump to whileCond
		BasicBlock oldEnd = currentBreakDest, oldIncr = currentContDest;
		currentBreakDest = whileEnd;
		currentContDest = whileCond;

		it.body.accept(this);

		currentBreakDest = oldEnd;
		currentContDest = oldIncr;

		addInst(new BrLabelInst(whileCond));


		currentBlock = whileEnd;
		currentFunction.blocks.add(whileEnd);
	}

	@Override
	public void visit(ForStmtNode it) {
		var forCond = new BasicBlock("for.cond");
		var forBody = new BasicBlock("for.body");
		var forIncr = new BasicBlock("for.incr");
		var forEnd = new BasicBlock("for.end");

		if (it.init != null) {
			it.init.accept(this);
		}
		if (it.initDef != null) {
			it.initDef.forEach(x -> {
				x.type.accept(this);
				var variable = new Variable(x.type.irType, x.id);
				var allocaInst = addInst(new AllocaInst(x.type.irType), x.id + ".ptr");
				addInst(new StoreInst(variable, allocaInst));
				currentScope.addEntity(x.id, allocaInst);
			});
		}
		addInst(new BrLabelInst(forCond));

		currentFunction.blocks.add(forCond);
		currentBlock = forCond;
		if (it.cond != null) {
			it.cond.accept(this);
			checkConditionBool(it.cond);
			addInst(new BrInst(it.cond.irValue, forBody, forEnd));
		} else {
			addInst(new BrLabelInst(forBody));
		}

		currentFunction.blocks.add(forBody);
		currentBlock = forBody;

		// prepare for while and continue
		// break: jump to forEnd
		// continue: jump to forIncr
		BasicBlock oldEnd = currentBreakDest, oldIncr = currentContDest;
		currentBreakDest = forEnd;
		currentContDest = forIncr;

		it.body.accept(this);

		currentBreakDest = oldEnd;
		currentContDest = oldIncr;

		addInst(new BrLabelInst(forIncr));

		currentFunction.blocks.add(forIncr);
		currentBlock = forIncr;
		if (it.incr != null) {
			it.incr.accept(this);
		}
		addInst(new BrLabelInst(forCond));

		currentBlock = forEnd;
		currentFunction.blocks.add(forEnd);
	}

	@Override
	public void visit(ControlStmtNode it) {
		switch (it.type) {
			case BREAK -> addInst(new BrLabelInst(currentBreakDest));
			case CONTINUE -> addInst(new BrLabelInst(currentContDest));
		}
	}

	@Override
	public void visit(EmptyStmtNode it) {

	}

	@Override
	public void visit(IntExprNode it) {
		it.irValue = new IntConstant(it.value);
	}

	@Override
	public void visit(BoolExprNode it) {
		it.irValue = it.value ? IntConstant.TRUE : IntConstant.FALSE;
	}
	@Override
	public void visit(StringExprNode it) {
		var strConstant = new StrConstant(it.value);
		strConstant.id = ".str";
		topModule.gConstant.add(strConstant);
		it.irValue = addInst(new GEPInst(PointerType.I8STAR,
				strConstant, IntConstant.ZERO, IntConstant.ZERO));
	}

	@Override
	public void visit(NullExprNode it) {
		// type of null constant is to be determined
		it.irValue = new NullConstant();
	}

	// todo: irPointer maintain
	@Override
	public void visit(AssignExprNode it) {
		it.right.accept(this);
		it.left.accept(this);
		// class / array = null
		// xx = xx

		// NULL check
		if (it.right.type.type == AST.Type.TypeEnum.NULL) {
			it.right.irValue.type = it.left.irValue.type;
		}

		addInst(new StoreInst(it.right.irValue, it.left.irPointer));
		it.irValue = it.right.irValue;
	}

	private BinaryInst.OpType getBinaryOpType(String s) {
		return switch(s) {
			case "+" -> BinaryInst.OpType.ADD_NSW;
			case "-" -> BinaryInst.OpType.SUB_NSW;
			case "*" -> BinaryInst.OpType.MUL_NSW;
			case "/" -> BinaryInst.OpType.SDIV;
			case "%" -> BinaryInst.OpType.SREM;
			case "<<" -> BinaryInst.OpType.SHL;
			case ">>" -> BinaryInst.OpType.ASHR;
			case "&" -> BinaryInst.OpType.AND;
			case "|" -> BinaryInst.OpType.OR;
			case "^" -> BinaryInst.OpType.XOR;
			case "||" -> BinaryInst.OpType.OR;
			case "&&" -> BinaryInst.OpType.AND;
			default -> null;
		};
	}

	private IcmpInst.OpType getCmpOpType(String s) {
		return switch(s) {
			case "==" -> IcmpInst.OpType.EQ;
			case "!=" -> IcmpInst.OpType.NE;
			case ">" -> IcmpInst.OpType.SGT;
			case ">=" -> IcmpInst.OpType.SGE;
			case "<" -> IcmpInst.OpType.SLT;
			case "<=" -> IcmpInst.OpType.SLE;
			default -> null;
		};
	}

	@Override
	public void visit(BinaryExprNode it) {
		if (it.op.equals("||") || it.op.equals("&&")) {
			binaryLogicHandle(it);
			return;
		}

		it.left.accept(this);
		it.right.accept(this);

		Value res = null;
		switch (it.op) {
			case "!=", "==" -> {
				// null check & type completion
				boolean leftNull = it.left.type.type == AST.Type.TypeEnum.NULL,
						rightNull = it.right.type.type == AST.Type.TypeEnum.NULL;
				if (leftNull && rightNull) {
					res = IntConstant.TRUE;
				} else if (leftNull) {
					it.left.irValue.type = it.right.irValue.type;
				} else if (rightNull) {
					it.right.irValue.type = it.left.irValue.type;
				}

				// string(maybe one null): strcmp
				// both null: true
				// other: icmp
				if (it.left.type.type == AST.Type.TypeEnum.STRING ||
						it.right.type.type == AST.Type.TypeEnum.STRING) {
					var callInst = addInst(new CallInst(topModule.gFunc.get("__mx_builtin_strcmp"),
							it.left.irValue, it.right.irValue), "strcmp.res");
					res = new IcmpInst(getCmpOpType(it.op), callInst, IntConstant.ZERO);
				} else if (!leftNull || !rightNull) {
					res = new IcmpInst(getCmpOpType(it.op), it.left.irValue, it.right.irValue);
				}
			}
			case "<", "<=", ">", ">=" -> {
				if (it.left.type.type == AST.Type.TypeEnum.STRING) {
					var callInst = addInst(new CallInst(topModule.gFunc.get("__mx_builtin_strcmp"), it.left.irValue, it.right.irValue));
					res = new IcmpInst(getCmpOpType(it.op), callInst, IntConstant.ZERO);
				} else { // int compare
					res = new IcmpInst(getCmpOpType(it.op), it.left.irValue, it.right.irValue);
				}
			}
			case "+" -> {
				if (it.left.type.type == AST.Type.TypeEnum.STRING) {
					res = new CallInst(topModule.gFunc.get("__mx_builtin_strcat"), it.left.irValue, it.right.irValue);
				} else { // int
					res = new BinaryInst(getBinaryOpType(it.op), it.left.irValue, it.right.irValue);
				}
			}
			case "-", "*", "/", "%", "<<", ">>", "&", "|", "^" -> {
				res = new BinaryInst(getBinaryOpType(it.op), it.left.irValue, it.right.irValue);
			}
		}
		if (res instanceof Inst newRes) {
			addInst(newRes);
		}
		it.irValue = res;
	}

	private void binaryLogicHandle(BinaryExprNode it) {
		it.left.accept(this);

		var logicRhs = new BasicBlock("logic.rhs");
		var logicEnd = new BasicBlock("logic.end");
		var pastBlock = currentBlock;
		boolean isAnd = it.op.equals("&&");

		if (isAnd) {
			addInst(new BrInst(it.left.irValue, logicRhs, logicEnd));
		} else {
			addInst(new BrInst(it.left.irValue, logicEnd, logicRhs));
		}

		currentFunction.blocks.add(logicRhs);
		currentBlock = logicRhs;
		it.right.accept(this);
		addInst(new BrLabelInst(logicEnd));

		currentFunction.blocks.add(logicEnd);
		currentBlock = logicEnd;

		if (isAnd){
			it.irValue = addInst(new PhiInst(new Pair<>(IntConstant.FALSE, pastBlock),
					new Pair<>(it.right.irValue, logicRhs)));
		} else {
			it.irValue = addInst(new PhiInst(new Pair<>(IntConstant.TRUE, pastBlock),
					new Pair<>(it.right.irValue, logicRhs)));
		}
	}

	@Override
	public void visit(BinaryBoolExprNode it) {
		visit((BinaryExprNode) it);
	}

	@Override
	public void visit(PrefixExprNode it) {
		it.value.accept(this);
		Inst res = null;
		switch (it.op) {
			case "++" -> {
				res = addInst(new BinaryInst(BinaryInst.OpType.ADD_NSW, it.value.irValue, IntConstant.ONE));
				addInst(new StoreInst(res, it.value.irPointer));
			}
			case "--" -> {
				res = addInst(new BinaryInst(BinaryInst.OpType.SUB_NSW, it.value.irValue, IntConstant.ONE));
				addInst(new StoreInst(res, it.value.irPointer));
			}
			case "~" -> res = addInst(new BinaryInst(BinaryInst.OpType.XOR, it.value.irValue, IntConstant.NEG_ONE));
			case "!" -> res = addInst(new BinaryInst(BinaryInst.OpType.XOR, it.value.irValue, IntConstant.TRUE));
			case "-" -> res = addInst(new BinaryInst(BinaryInst.OpType.SUB_NSW, IntConstant.ZERO, it.value.irValue));
		}
		it.irValue = res;
		if (it.assignable) { // ++ or --
			it.irPointer = it.value.irPointer;
		}
	}

	@Override
	public void visit(SuffixExprNode it) {
		it.value.accept(this);
		Inst res = null;
		switch (it.op) {
			case "++" -> res = new BinaryInst(BinaryInst.OpType.ADD_NSW,
					it.value.irValue, IntConstant.ONE);
			case "--" -> res = new BinaryInst(BinaryInst.OpType.SUB_NSW,
					it.value.irValue, IntConstant.ONE);
		}
		addInst(res);
		addInst(new StoreInst(res, it.value.irPointer));
		it.irValue = it.value.irValue;
	}

	@Override
	public void visit(LambdaExprNode it) {
		// lambda is not implemented
	}

	// val: t*; return: t
	private StructType getStructType(Value val) {
		return (StructType) ((PointerType)val.type).baseType;
	}

	@Override
	//type: i8* string; T** array; T* class
	public void visit(MemberExprNode it) {
		it.base.accept(this);
		if (it.isFunc) {
			if (it.isStr) { // string
				var funcName =  "__mx_str_" + it.id;
				it.irValue = topModule.gFunc.get(funcName);
				it.basePointer = it.base.irValue;
			} else if (it.isArray) { // arr.size()
				it.irValue = null;
				it.basePointer = it.base.irValue;
			} else {
				var funcName = getStructType(it.base.irValue).id + "." + it.id;
				it.irValue = topModule.gFunc.get(funcName);
				it.basePointer = it.base.irValue;
			}
		} else {
			var idx = getStructType(it.base.irValue).fieldIdx.get(it.id);
			var gepInst = addInst(new GEPInst(it.base.irValue, IntConstant.ZERO, new IntConstant(idx)));
			var loadInst = addInst(new LoadInst(gepInst), "load.");
			it.irValue = loadInst;
			it.irPointer = gepInst;
		}
	}

	public Value getBestMatch(ArrayList<Value> paramList, Function... funcs) {
		var filter = new ArrayList<>(Arrays.asList(funcs));
		filter.removeIf(Objects::isNull);
		out: for (var x : filter) {
			for (int i = 0; i < paramList.size(); ++i) {
				var p = ((FuncType)x.type).argType.get(i);
				var q = paramList.get(i).type;
				var r = p.equals(q);
				if (!((FuncType)x.type).argType.get(i).equals(paramList.get(i).type)) {
					continue out;
				}
			}
			return x;
		}
		// no match
		return null;
	}

	@Override
	public void visit(FuncCallExprNode it) {
		it.func.accept(this);

		// arr.size()
		if (it.func instanceof MemberExprNode mFunc && mFunc.irValue == null) {
			var i32Ptr1 = addInst(new BitcastInst(I32STAR, mFunc.basePointer), "i32.ptr.1");
			var i32Ptr0 = addInst(new GEPInst(i32Ptr1, IntConstant.NEG_ONE), "i32.ptr.0");
			var loadInst = addInst(new LoadInst(i32Ptr0), "arr.size");
			it.irValue = loadInst;
			return;
		}

		// get arg list
		var irParamList = new ArrayList<Value>();
		it.argList.forEach(x -> {
			x.accept(this);
			irParamList.add(x.irValue);
		});

		Inst call = null;
		if (it.func instanceof MemberExprNode mFunc) {
			call = new CallInst(it.func.irValue);
			call.addUse(mFunc.basePointer);
		} else {
			// VarExprNode
			// get best match
			var node = (VarExprNode)it.func;
			// we need a function overload and find match
			// ugly, but I am tired to revise it
			Type tmp = null;
			if (node.classFunc != null) {
				tmp = ((FuncType)node.classFunc.type).argType.remove(0);
			}
			// order: class func first, global func last
			var func = getBestMatch(irParamList, node.classFunc, (Function) node.irValue);

			if (node.classFunc != null) {
				((FuncType)node.classFunc.type).argType.add(0, tmp);
			}

			call = new CallInst(func);

			if (func == node.classFunc) { // class func; parameter "this"
				var pointer = currentScope.getEntity("this", true);
				var thisLoadInst = addInst(new LoadInst(pointer), "this.self");
				call.addUse(thisLoadInst);
			}
		}

		// add parameter; null check
		int cnt = 0;
		for (var x : irParamList) {
			if (x instanceof NullConstant) {
				x.type = ((FuncType)it.func.irValue.type).argType.get(cnt);
			}
			call.addUse(x);
			++cnt;
		}

		it.irValue = addInst(call, "call.");
	}

	@Override
	public void visit(SubscriptExprNode it) {
		it.base.accept(this);
		it.index.accept(this);
		it.irPointer = addInst(new GEPInst(it.base.irValue, it.index.irValue));
		it.irValue = addInst(new LoadInst(it.irPointer));
	}


	// type is PointerType -> StructType (aka T*)
	private Value simpleNewHandle(Type type) {
		// allocate
		Type base = ((PointerType)type).getDereference();
		var newSize = new IntConstant(base.getSize());
		var newPtr = addInst(new CallInst(topModule.gFunc.get("__mx_builtin_malloc"), newSize), "new.ptr");
		var bitcastPtr = addInst(new BitcastInst(type, newPtr), "bitcast.ptr");

		// init
		// constructor exists: call constructor
		boolean ok = false;
		if (base instanceof StructType structType) {
			var cons = topModule.gFunc.get(structType.id + constructorSuffix);
			if (cons != null) {
				ok = true;
				addInst(new CallInst(cons, bitcastPtr));
			}
		}
		// otherwise: memset
		if (!ok) {
			addInst(new CallInst(topModule.gFunc.get("__mx_builtin_memset"),
					newPtr, IntConstant.ZERO, newSize));
		}
		return bitcastPtr;
	}


	// quite hard to implement
	// dim >= 1
	// sizes.size() >= 1
	// process:
	//  type: ptr(type,dim)
	//  cnt: sizes.get(startIndex); size = sizeof(type)*cnt
	//  init: 1. start < sizes.size() - 1
	//            i = 0...cnt-1
	//            store arrayNewHandle(type, dim - 1, sizes, startIndex - 1), &ptr[i]
	//        2.       ==
	//        2.1. dim == sizes.size() && has constructor
	//            i = 0...cnt-1
	//            call constructor(&ptr[i])
	//        2.2. otherwise
	//            memset(ptr, 0, size, false)

	// new T[]: sizeof(T*)
	// new int[]: sizeof(int)
	private Value arrayNewHandle(Type type, int dim, ArrayList<Value> sizes, int startIndex) {
		// allocate
		var baseSize = new IntConstant(dim == 1 ? type.getSize() : ptrSize);
		var newSize0 = addInst(new BinaryInst(BinaryInst.OpType.MUL_NSW,
				sizes.get(startIndex), baseSize), "new.size.0");
		var newSize1 = addInst(new BinaryInst(BinaryInst.OpType.ADD_NSW,
				newSize0, IntConstant.FOUR), "new.size.1");

		var i8Ptr0 = addInst(new CallInst(topModule.gFunc.get("__mx_builtin_malloc"), newSize1), "i8.ptr.0");
		var i32Ptr0 = addInst(new BitcastInst(I32STAR, i8Ptr0), "i32.ptr.0");
		addInst(new StoreInst(sizes.get(startIndex), i32Ptr0));
		var i32Ptr1 = addInst(new GEPInst(i32Ptr0, IntConstant.ONE), "i32.ptr.1");
		var typePtr = addInst(new BitcastInst(new PointerType(type, dim), i32Ptr1), "type.ptr");

		// init
		if (startIndex < sizes.size() - 1) {
			var whileStartPackage = whileStart(sizes.get(startIndex));
			var res = arrayNewHandle(type, dim - 1, sizes, startIndex + 1);
			var arrayI = addInst(new GEPInst(typePtr, whileStartPackage.whileIValue), "array.i");
			addInst(new StoreInst(res, arrayI));
			whileEnd(whileStartPackage);
		} else {
			// ptr array init: memset
			var i8Ptr1 = addInst(new BitcastInst(I8STAR, typePtr), "i8.ptr.1");
			addInst(new CallInst(topModule.gFunc.get("__mx_builtin_memset"),
						i8Ptr1, IntConstant.ZERO, newSize0));
		}
		return typePtr;
	}


	@Override
	public void visit(NewExprNode it) {
		it.newType.accept(this);
		ArrayList<Value> sizes = new ArrayList<>();
		for (var x : it.sizes) {
			x.accept(this);
			sizes.add(x.irValue);
		}
		if (it.newType.dim != 0) {
			var baseTypeNode = new TypeNode(it.newType.type, it.newType.identifier, 0, null);
			baseTypeNode.accept(this);
			it.irValue = arrayNewHandle(baseTypeNode.irType, it.newType.dim, sizes, 0);
		} else {
			it.irValue = simpleNewHandle(it.newType.irType);
		}
	}

	@Override
	public void visit(ThisExprNode it) {
		var pointer = currentScope.getEntity("this", true);
		var thisLoadInst = addInst(new LoadInst(pointer), "this.self");
		var valLoadInst = addInst(new LoadInst(pointer), "this.value");
		it.irValue = valLoadInst;
		it.irPointer = thisLoadInst;
	}

	@Override
	public void visit(VarExprNode it) {
		if (it.isFunc) {
			// res.irValue: func found in global
			// res.classFunc: func found in class
			if (currentStruct != null) {
				it.classFunc = topModule.gFunc.get(currentStruct.id + "." + it.id);
			}
			it.irValue = topModule.gFunc.get(it.id);
		} else {
			// 2022/12/3: fix a error
			// first check in Scope
			var result = currentScope.getEntityInClass(it.id, true);
			// then check in Class
			var idx = currentStruct != null ? currentStruct.fieldIdx.get(it.id) : null;
			if (idx != null && (result.a == null || result.b)) {
				var thisLoadInst = addInst(
						new LoadInst(currentScope.getEntity("this", true)), "this.self");
				var gepInst = addInst(new GEPInst(thisLoadInst, IntConstant.ZERO, new IntConstant(idx)));
				var loadInst = addInst(new LoadInst(gepInst), "this." + it.id + ".value");
				it.irValue = loadInst;
				it.irPointer = gepInst;
			} else {
				var pointer = result.a;
				var loadInst = addInst(new LoadInst(pointer), it.id + ".value");
				it.irValue = loadInst;
				it.irPointer = pointer;
			}
		}
	}

	@Override
	public void visit(TypeNode it) {
		if (it.irType != null) {
			return;
		}
		var irTypeBase = switch (it.type) {
			case VOID -> Type.VOID;
			case INT -> IntType.INT32;
			case BOOL -> IntType.INT1;
			case STRING -> PointerType.I8STAR;
			case CLASS -> new PointerType(topModule.gStruct.get(it.identifier));
			default -> null;
		};
		if (it.dim == 0) {
			it.irType = irTypeBase;
		} else {
			it.irType = new PointerType(irTypeBase, it.dim);
		}
	}


	// tool functions

	private Inst addInst(Inst inst, String id) {
		currentBlock.addInst(inst);
		inst.id = id;

		// maintain blocks
//		if (inst instanceof BrLabelInst newInst) {
//			BasicBlock.addLink(currentBlock, (BasicBlock) newInst.getUse(0));
//		} else if (inst instanceof BrInst newInst) {
//			BasicBlock.addLink(currentBlock, (BasicBlock) newInst.getUse(0));
//			BasicBlock.addLink(currentBlock, (BasicBlock) newInst.getUse(1));
//		}
		return inst;
	}

	private Inst addInst(Inst inst) {
		return addInst(inst, "");
	}

	private static class WhileStartPackage {
		public Value whileI, whileIValue;
		public BasicBlock whileCond, whileEnd;

		public WhileStartPackage(Value whileI, Value whileIValue, BasicBlock whileCond, BasicBlock whileEnd) {
			this.whileI = whileI;
			this.whileIValue = whileIValue;
			this.whileCond = whileCond;
			this.whileEnd = whileEnd;
		}
	}

	/**
	 * usage:
	 * var whileStartPackage = whileStart(N);
	 * do something...
	 * whileEnd(whileStartPackage);
	 * idiotic java grammar
	 */
	private WhileStartPackage whileStart(Value whileN) {
		var whileI = addInst(new AllocaInst(IntType.INT32), "while.i");
		addInst(new StoreInst(IntConstant.ZERO, whileI));

		BasicBlock whileCond = new BasicBlock("while.cond"),
				whileBody = new BasicBlock("while.body"),
				whileEnd = new BasicBlock("while.end");

		addInst(new BrLabelInst(whileCond));

		// while.cond
		currentFunction.blocks.add(whileCond);
		currentBlock = whileCond;
		var whileIValue = addInst(new LoadInst(whileI), "while.i.value");
		var cmp = addInst(new IcmpInst(IcmpInst.OpType.SLT, whileIValue, whileN), "cmp");
		addInst(new BrInst(cmp, whileBody, whileEnd));

		// while.body
		currentFunction.blocks.add(whileBody);
		currentBlock = whileBody;

		return new WhileStartPackage(whileI, whileIValue, whileCond, whileEnd);
	}

	private void whileEnd(WhileStartPackage param) {
		// while.body
		var whileIValueInc = addInst(new BinaryInst(BinaryInst.OpType.ADD_NSW, param.whileIValue, IntConstant.ONE), "while.i.value.inc");
		addInst(new StoreInst(whileIValueInc, param.whileI));
		addInst(new BrLabelInst(param.whileCond));
		// while.end
		currentFunction.blocks.add(param.whileEnd);
		currentBlock = param.whileEnd;
	}
}
