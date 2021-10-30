package AST;

import Util.Position;

import java.util.ArrayList;

public class NewExprNode extends ExprNode {
    public TypeNode type;
    public ArrayList<ExprNode> sizes;

    public NewExprNode(TypeNode type, ArrayList<ExprNode> sizes, Position pos) {
        super(pos);
        this.type = type;
        this.sizes = sizes;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
