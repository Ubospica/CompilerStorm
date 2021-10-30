package AST;

import Util.Position;

public class VarDefSubStmtNode extends StmtNode {
    int
    public VarDefSubStmtNode(Position pos) {
        super(pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
