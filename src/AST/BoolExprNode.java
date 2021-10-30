package AST;

import Util.Position;

public class BoolExprNode extends AtomExprNode {
    boolean value;

    public BoolExprNode(boolean value, Position pos) {
        super(pos);
        this.value = value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
