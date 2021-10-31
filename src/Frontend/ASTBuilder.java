package Frontend;

import AST.*;
import AST.Statement.ControlFlow.ControlStmtNode;
import AST.Statement.ControlFlow.ForStmtNode;
import AST.Statement.ControlFlow.ReturnStmtNode;
import AST.Statement.ControlFlow.WhileStmtNode;
import AST.Definition.*;
import AST.Expression.*;
import AST.Expression.Atom.*;
import AST.Statement.*;
import Parser.MxBaseVisitor;
import Parser.MxParser;
import Util.Error.SyntaxError;
import Util.Position;

import java.util.ArrayList;

public class ASTBuilder extends MxBaseVisitor<ASTNode> {
    @Override
    public ASTNode visitProgram(MxParser.ProgramContext ctx) {
        ProgramNode res = new ProgramNode(new Position(ctx));
        if (ctx.programSub() != null) {
            for (var it : ctx.programSub()) {
                ASTNode cur = visit(it);
                if (cur instanceof VarDefStmtNode) {
                    res.body.addAll(((VarDefStmtNode) cur).varList);
                }
                res.body.add(cur);
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
            return new TypeNode(TypeNode.Types.VOID, null, 0, new Position(ctx));
        } else {
            return visit(ctx.type());
        }
    }

    @Override
    public ASTNode visitTypeSub(MxParser.TypeSubContext ctx) {
        TypeNode.Types type;
        String id = null;
        switch (ctx.getText()) {
            case "int" -> type = TypeNode.Types.INT;
            case "bool" -> type = TypeNode.Types.BOOL;
            case "string" -> type = TypeNode.Types.STRING;
            default -> {
                type = TypeNode.Types.CLASS;
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
                body.add((StmtNode) visit(i));
            }
        }
        return new BlockStmtNode(body, new Position(ctx));
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
                field.add((VarDefSubStmtNode) visit(i));
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
    public ASTNode visitWhileStmt(MxParser.WhileStmtContext ctx) {
        ExprNode cond = (ExprNode)visit(ctx.expression());
        return new WhileStmtNode(cond, (BlockStmtNode)visit(ctx.statement()), new Position(ctx));
    }

    @Override
    public ASTNode visitForStmt(MxParser.ForStmtContext ctx) {
        ExprNode init = null, cond = null, incr = null;
        if (ctx.init != null) {
            init = (ExprNode) visit(ctx.init);
        }
        if (ctx.cond != null) {
            cond = (ExprNode) visit(ctx.cond);
        }
        if (ctx.incr != null) {
            incr = (ExprNode) visit(ctx.incr);
        }
        return new ForStmtNode(init, cond, incr, (StmtNode)visit(ctx.statement()), new Position(ctx));
    }

    @Override
    public ASTNode visitLiteral(MxParser.LiteralContext ctx) {
        if (ctx.IntegerLiteral() != null) {
            return new IntExprNode(Integer.parseInt(ctx.IntegerLiteral().getText()), new Position(ctx));
        } else if (ctx.BooleanLiteral() != null) {
            return new BoolExprNode(ctx.BooleanLiteral().getText().equals("true"), new Position(ctx));
        } else if (ctx.StringLiteral() != null) {
            String val = ctx.StringLiteral().getText();
            val = val.substring(1, val.length() - 2);
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
        return new FuncCallExprNode((ExprNode) visit(ctx.expression()), argList, new Position(ctx));
    }

    @Override
    public ASTNode visitLambda(MxParser.LambdaContext ctx) {
        var param = new ArrayList<VarDefSubStmtNode>();
        if (ctx.paramList().param() != null) {
            for (var i : ctx.paramList().param()) {
                param.add((VarDefSubStmtNode) visit(i));
            }
        }
        var body = (BlockStmtNode)visit(ctx.block());
        return new LambdaExprNode(param, body, new Position(ctx));
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
