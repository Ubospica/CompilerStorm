package AST;

import Util.Position;

//expression like `a; //a is a variable`
public class VarExprNode extends AtomExprNode {
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
