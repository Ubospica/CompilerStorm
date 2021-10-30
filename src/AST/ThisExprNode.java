package AST;

import Util.Position;

public class ThisExprNode extends AtomExprNode {
    public ThisExprNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
