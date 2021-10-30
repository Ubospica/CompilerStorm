package AST;

import Util.Position;

public class ReturnStmtNode extends StmtNode {
    public ExprNode value;
    public ReturnStmtNode(ExprNode value, Position pos) {
        super(pos);
        this.value = value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
