package AST;

import Util.Position;

public class ForStmtNode extends StmtNode {
    public ExprNode init, cond, incr;
    public StmtNode body;

    public ForStmtNode(ExprNode init, ExprNode cond, ExprNode incr, StmtNode body, Position pos) {
        super(pos);
        this.init = init;
        this.cond = cond;
        this.incr = incr;
        this.body = body;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
