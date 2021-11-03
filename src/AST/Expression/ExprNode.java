package AST.Expression;

import AST.ASTNode;
import AST.Scope.Type.Type;
import Util.Position;

public abstract class ExprNode extends ASTNode {
    public Type type;
    public boolean assignable = false;

    public ExprNode(Position pos) {
        super(pos);
    }
}
