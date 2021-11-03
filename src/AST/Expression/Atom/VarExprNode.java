package AST.Expression.Atom;

import AST.ASTVisitor;
import Util.Position;

public class VarExprNode extends AtomExprNode {
    public boolean isFunc = false;
    public String id;

    public VarExprNode(String id, Position pos) {
        super(pos);
        this.id = id;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
