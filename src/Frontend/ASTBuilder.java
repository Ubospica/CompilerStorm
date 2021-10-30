package Frontend;

import AST.*;
import Parser.MxBaseVisitor;
import Parser.MxParser;
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
        for (var i : ctx.paramList().param()) {
            param.add((VarDefSubStmtNode)visit(i));
        }
        var body = (BlockStmtNode)visit(ctx.block());
        return new FuncDefNode(returnType, id, param, body, new Position(ctx));
    }

    @Override
    public ASTNode visitReturnType(MxParser.ReturnTypeContext ctx) {
        if (ctx.Void() != null) {
            return new TypeNode(TypeNode.Types.VOID, null, 0, new Position(ctx));
        }
        else {
            return visit(ctx.type());
        }
    }

    @Override
    public ASTNode visitType(MxParser.TypeContext ctx) {
        TypeNode.Types type;
        String id = null;
        switch (ctx.typeSub().getText()) {
            case "int" -> type = TypeNode.Types.INT;
            case "bool" -> type = TypeNode.Types.BOOL;
            case "string" -> type = TypeNode.Types.STRING;
            default -> {
                type = TypeNode.Types.CLASS;
                id = ctx.typeSub().getText();
            }
        }
        return new TypeNode(type, id, (ctx.getChildCount() - 1) / 2, new Position(ctx));
    }

    @Override
    public ASTNode visitBlock(MxParser.BlockContext ctx) {
        ArrayList<StmtNode> body = new ArrayList<>();
        for (var i : ctx.statement()) {
            body.add((StmtNode)visit(i));
        }
        return new BlockStmtNode(body, new Position(ctx));
    }

    @Override
    public ASTNode visitVarDef(MxParser.VarDefContext ctx) {
        ArrayList<VarDefSubStmtNode> varList;
        for (var i : ctx.varDefSub()) {

        }
    }

    @Override
    public ASTNode visitVarDef(MxParser.VarDefStmtContext ctx) {
        VarDefStmtNode ans = new VarDefStmtNode(new Position(ctx));
        TypeNode type = (TypeNode) visit(ctx.varDef().type());
        for (MxParser.VarDefSubContext x : ctx.varDef().varDefSub()) {
            VarDefSubStmt t = (VarDefSubStmt) visit(x);
            t.type = type;
            ans.varList.add(t);
        }
        return ans;
    }
}
