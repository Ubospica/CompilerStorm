package AST.Expression;

import AST.ASTVisitor;
import Util.Position;

public class MemberExprNode extends ExprNode {
    ExprNode base;
    String id;

    public MemberExprNode(ExprNode base, String id, Position pos) {
        super(pos);
        this.base = base;
        this.id = id;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
