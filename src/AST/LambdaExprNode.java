package AST;

import Util.Position;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;

public class LambdaExprNode extends ExprNode {
    public ArrayList<VarDefSubStmtNode> paramList;
    public BlockStmtNode body;

    public LambdaExprNode(ArrayList<VarDefSubStmtNode> param, BlockStmtNode body, Position pos) {
        super(pos);
        this.paramList = param;
        this.body = body;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
