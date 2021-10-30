package AST;

import Util.Position;

public class IntExprNode extends AtomExprNode {
    public int value;

    public IntExprNode(int value, Position pos) {
        super(pos);
        this.value = value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
