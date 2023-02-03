package Frontend;

import AST.*;
import AST.Statement.ControlFlow.*;
import AST.Definition.*;
import AST.Expression.*;
import AST.Expression.Atom.*;
import AST.Statement.*;
import AST.Type.TypeEnum;
import Parser.MxBaseVisitor;
import Parser.MxParser;
import Util.Error.SyntaxError;
import Util.Position;

import java.util.ArrayList;
import java.util.Arrays;

public class ASTBuilder extends MxBaseVisitor<ASTNode> {

	public ASTBuilder() { }


	@Override
	public ASTNode visitProgram(MxParser.ProgramContext ctx) {
		ProgramNode res = new ProgramNode(new Position(ctx));
		if (ctx.programSub() != null) {
			for (var it : ctx.programSub()) {
				ASTNode cur = visit(it);
				if (cur instanceof VarDefStmtNode newCur) {
					for (var i : newCur.varList) {
						i.isGlobal = true;
						res.body.add(i);
					}
				}
				else {
					res.body.add(cur);
				}
			}
		}
		return res;
	}

	@Override
	public ASTNode visitProgramSub(MxParser.ProgramSubContext ctx) {
		if (ctx.classDef() != null) return visit(ctx.classDef());
		else if (ctx.funcDef() != null) return visit(ctx.funcDef());
		else return visit(ctx.varDefStmt());
	}

	@Override
	public ASTNode visitFuncDef(MxParser.FuncDefContext ctx) {
		var returnType = (TypeNode)visit(ctx.returnType());
		String id = ctx.Identifier().getText();
		var param = new ArrayList<VarDefSubStmtNode>();
		if (ctx.paramList().param() != null) {
			for (var i : ctx.paramList().param()) {
				param.add((VarDefSubStmtNode) visit(i));
			}
		}
		var body = (BlockStmtNode)visit(ctx.block());
		return new FuncDefNode(returnType, id, param, body, new Position(ctx));
	}

	@Override
	public ASTNode visitReturnType(MxParser.ReturnTypeContext ctx) {
		if (ctx.Void() != null) {
			return new TypeNode(TypeEnum.VOID, null, 0, new Position(ctx));
		} else {
			return visit(ctx.type());
		}
	}

	@Override
	public ASTNode visitTypeSub(MxParser.TypeSubContext ctx) {
		TypeEnum type;
		String id = null;
		switch (ctx.getText()) {
			case "int" -> type = TypeEnum.INT;
			case "bool" -> type = TypeEnum.BOOL;
			case "string" -> type = TypeEnum.STRING;
			default -> {
				type = TypeEnum.CLASS;
				id = ctx.getText();
			}
		}
		return new TypeNode(type, id, 0, new Position(ctx));
	}

	@Override
	public ASTNode visitType(MxParser.TypeContext ctx) {
		TypeNode type = (TypeNode) visit(ctx.typeSub());
		type.dim = (ctx.getChildCount() - 1) / 2;
		return type;
	}

	@Override
	public ASTNode visitBlock(MxParser.BlockContext ctx) {
		ArrayList<StmtNode> body = new ArrayList<>();
		if (ctx.statement() != null) {
			for (var i : ctx.statement()) {
				StmtNode cur = (StmtNode) visit(i);
				if (cur instanceof VarDefStmtNode) {
					body.addAll(((VarDefStmtNode) cur).varList);
				}
				else {
					body.add(cur);
				}
			}
		}
		return new BlockStmtNode(body, new Position(ctx));
	}

	@Override
	public ASTNode visitVarDefStmt(MxParser.VarDefStmtContext ctx) {
		return visit(ctx.varDef());
	}

	@Override
	public ASTNode visitVarDef(MxParser.VarDefContext ctx) {
		var type = (TypeNode)visit(ctx.type());
		var varList = new ArrayList<VarDefSubStmtNode>();
		for (var i : ctx.varDefSub()) {
			var node = (VarDefSubStmtNode)visit(i);
			node.type = type;
			varList.add(node);
		}
		return new VarDefStmtNode(varList, new Position(ctx));
	}

	@Override
	public ASTNode visitVarDefSub(MxParser.VarDefSubContext ctx) {
		ExprNode init = ctx.expression() == null ? null : (ExprNode)visit(ctx.expression());
		return new VarDefSubStmtNode(ctx.Identifier().getText(), init, new Position(ctx));
	}

	@Override
	public ASTNode visitConstructorDef(MxParser.ConstructorDefContext ctx) {
		return new ConstructorDefNode(ctx.Identifier().getText(), (BlockStmtNode)visit(ctx.block()),
				new Position(ctx));
	}

	@Override
	public ASTNode visitClassDef(MxParser.ClassDefContext ctx) {
		ArrayList<VarDefSubStmtNode> field = new ArrayList<>();
		ArrayList<ConstructorDefNode> constructor = new ArrayList<>();
		ArrayList<FuncDefNode> method = new ArrayList<>();
		if (ctx.varDefStmt() != null) {
			for (var i : ctx.varDefStmt()) {
				field.addAll(((VarDefStmtNode) visit(i)).varList);
			}
		}
		if (ctx.constructorDef() != null) {
			for (var i : ctx.constructorDef()) {
				constructor.add((ConstructorDefNode) visit(i));
			}
		}
		if (ctx.funcDef() != null) {
			for (var i : ctx.funcDef()) {
				method.add((FuncDefNode) visit(i));
			}
		}
		return new ClassDefNode(ctx.Identifier().getText(), field, constructor, method, new Position(ctx));
	}

	@Override
	public ASTNode visitReturnStmt(MxParser.ReturnStmtContext ctx) {
		ExprNode value = ctx.expression() == null ? null : (ExprNode)visit(ctx.expression());
		return new ReturnStmtNode(value, new Position(ctx));
	}

	@Override
	public ASTNode visitControlStmt(MxParser.ControlStmtContext ctx) {
		ControlStmtNode.Type ty;
		if (ctx.Continue() != null) ty = ControlStmtNode.Type.CONTINUE;
		else ty = ControlStmtNode.Type.BREAK;
		return new ControlStmtNode(ty, new Position(ctx));
	}

	@Override
	public ASTNode visitExprStmt(MxParser.ExprStmtContext ctx) {
		return new ExprStmtNode((ExprNode) visit(ctx.expression()), new Position(ctx));
	}

	@Override
	public ASTNode visitEmptyStmt(MxParser.EmptyStmtContext ctx) {
		return new EmptyStmtNode(new Position(ctx));
	}

	@Override
	public ASTNode visitIfStmt(MxParser.IfStmtContext ctx) {
		var thenStmt = (StmtNode)visit(ctx.trueStmt);
		var elseStmt = ctx.falseStmt == null ? null : (StmtNode) visit(ctx.falseStmt);
		if (!(thenStmt instanceof BlockStmtNode)) {
			thenStmt = new BlockStmtNode(new ArrayList<>(Arrays.asList(thenStmt)), thenStmt.pos);
		}
		if (elseStmt != null && !(elseStmt instanceof BlockStmtNode)) {
			elseStmt = new BlockStmtNode(new ArrayList<>(Arrays.asList(elseStmt)), elseStmt.pos);
		}
		return new IfStmtNode((ExprNode)visit(ctx.expression()), thenStmt, elseStmt, new Position(ctx));
	}

	@Override
	public ASTNode visitWhileStmt(MxParser.WhileStmtContext ctx) {
		ExprNode cond = (ExprNode)visit(ctx.expression());
		var thenStmt = (StmtNode)visit(ctx.statement());
		if (!(thenStmt instanceof BlockStmtNode)) {
			thenStmt = new BlockStmtNode(new ArrayList<>(Arrays.asList(thenStmt)), thenStmt.pos);
		}
		return new WhileStmtNode(cond, thenStmt, new Position(ctx));
	}

	@Override
	public ASTNode visitForStmt(MxParser.ForStmtContext ctx) {
		ExprNode init = null, cond = null, incr = null;
		ArrayList<VarDefSubStmtNode> initDef = null;
		if (ctx.initDef != null) {
			initDef = ((VarDefStmtNode) visit(ctx.initDef)).varList;
		}
		if (ctx.init != null) {
			init = (ExprNode) visit(ctx.init);
		}
		if (ctx.cond != null) {
			cond = (ExprNode) visit(ctx.cond);
		}
		if (ctx.incr != null) {
			incr = (ExprNode) visit(ctx.incr);
		}
		var thenStmt = (StmtNode)visit(ctx.statement());
		if (!(thenStmt instanceof BlockStmtNode)) {
			thenStmt = new BlockStmtNode(new ArrayList<>(Arrays.asList(thenStmt)), thenStmt.pos);
		}
		return new ForStmtNode(init, initDef, cond, incr, thenStmt, new Position(ctx));
	}

	@Override
	public ASTNode visitLiteral(MxParser.LiteralContext ctx) {
		if (ctx.IntegerLiteral() != null) {
			return new IntExprNode(Integer.parseInt(ctx.IntegerLiteral().getText()), new Position(ctx));
		} else if (ctx.BooleanLiteral() != null) {
			return new BoolExprNode(ctx.BooleanLiteral().getText().equals("true"), new Position(ctx));
		} else if (ctx.StringLiteral() != null) {
			String val = ctx.StringLiteral().getText();
			if (val.length() == 2) {
				val = "";
			} else {
				val = val.substring(1, val.length() - 1);
			}
			return new StringExprNode(val, new Position(ctx));
		} else {
			return new NullExprNode(new Position(ctx));
		}
	}

	@Override
	public ASTNode visitPrimary(MxParser.PrimaryContext ctx) {
		if (ctx.Identifier() != null) {
			return new VarExprNode(ctx.Identifier().toString(), new Position(ctx));
		} else if (ctx.literal() != null) {
			return visit(ctx.literal());
		} else {
			return new ThisExprNode(new Position(ctx));
		}
	}

	@Override
	public ASTNode visitBinaryExpr(MxParser.BinaryExprContext ctx) {
		return new BinaryExprNode((ExprNode) visit(ctx.src1), (ExprNode) visit(ctx.src2),
				ctx.op.getText(), new Position(ctx));
	}


	@Override
	public ASTNode visitBinaryBoolExpr(MxParser.BinaryBoolExprContext ctx) {
		return new BinaryBoolExprNode((ExprNode) visit(ctx.src1), (ExprNode) visit(ctx.src2),
				ctx.op.getText(), new Position(ctx));
	}

	@Override
	public ASTNode visitAssignExpr(MxParser.AssignExprContext ctx) {
		return new AssignExprNode((ExprNode) visit(ctx.src1), (ExprNode) visit(ctx.src2), new Position(ctx));
	}

	@Override
	public ASTNode visitPrefixExpr(MxParser.PrefixExprContext ctx) {
		return new PrefixExprNode((ExprNode) visit(ctx.expression()), ctx.op.getText(), new Position(ctx));
	}

	@Override
	public ASTNode visitSuffixExpr(MxParser.SuffixExprContext ctx) {
		return new SuffixExprNode((ExprNode) visit(ctx.expression()), ctx.op.getText(), new Position(ctx));
	}

	@Override
	public ASTNode visitMemberAccessExpr(MxParser.MemberAccessExprContext ctx) {
		return new MemberExprNode((ExprNode) visit(ctx.expression()),
				ctx.Identifier().toString(), new Position(ctx));
	}

	@Override
	public ASTNode visitFuncCallExpr(MxParser.FuncCallExprContext ctx) {
		ArrayList<ExprNode> argList = new ArrayList<>();
		if (ctx.expressionList().expression() != null) {
			for (var i : ctx.expressionList().expression()) {
				argList.add((ExprNode) visit(i));
			}
		}

		var left = (ExprNode) visit(ctx.expression());
		if (left instanceof MemberExprNode) {
			((MemberExprNode)left).isFunc = true;
		} else if (left instanceof VarExprNode) {
			((VarExprNode)left).isFunc = true;
		}

		return new FuncCallExprNode(left, argList, new Position(ctx));
	}

	@Override
	public ASTNode visitParenExpr(MxParser.ParenExprContext ctx) {
		return visit(ctx.expression());
	}

	@Override
	public ASTNode visitParam(MxParser.ParamContext ctx) {
		var node = new VarDefSubStmtNode(ctx.Identifier().getText(), null, new Position(ctx));
		node.type = (TypeNode)visit(ctx.type());
		return node;
	}

	@Override
	public ASTNode visitLambda(MxParser.LambdaContext ctx) {
		var param = new ArrayList<VarDefSubStmtNode>();
		if (ctx.paramList().param() != null) {
			for (var i : ctx.paramList().param()) {
				param.add((VarDefSubStmtNode) visit(i));
			}
		}
		var isCapture = ctx.captureList().getChildCount() != 0;
		var body = (BlockStmtNode)visit(ctx.block());
		return new LambdaExprNode(param, isCapture, body, new Position(ctx));
	}

	@Override
	public ASTNode visitSubscriptExpr(MxParser.SubscriptExprContext ctx) {
		return new SubscriptExprNode((ExprNode) visit(ctx.base), (ExprNode) visit(ctx.index), new Position(ctx));
	}


	@Override
	public ASTNode visitSimpleNewExpr(MxParser.SimpleNewExprContext ctx) {
		return new NewExprNode((TypeNode) visit(ctx.typeSub()), new ArrayList<>(), new Position(ctx));
	}

	@Override
	public ASTNode visitErrArrayNewExpr(MxParser.ErrArrayNewExprContext ctx) {
		throw new SyntaxError("Error in new array subscript", new Position(ctx));
	}

	@Override
	public ASTNode visitArrayNewExpr(MxParser.ArrayNewExprContext ctx) {
		var ty = (TypeNode) visit(ctx.typeSub());
		var sizes = new ArrayList<ExprNode>();
		for (var i : ctx.newArraySize().expression()) {
			sizes.add((ExprNode)visit(i));
		}
		ty.dim = (ctx.newArraySize().getChildCount() - sizes.size()) / 2;
		return new NewExprNode(ty, sizes, new Position(ctx));
	}

	@Override
	public ASTNode visitClassNewExpr(MxParser.ClassNewExprContext ctx) {
		return new NewExprNode((TypeNode) visit(ctx.typeSub()), new ArrayList<>(), new Position(ctx));
	}
}
