package AST;

import Util.Position;

public class VarDefSubStmtNode extends StmtNode {
    public TypeNode type;
    public String id;
    public ExprNode init;

    //'type' field is assigned by caller
    public VarDefSubStmtNode(String id, ExprNode init, Position pos) {
        super(pos);
        this.id = id;
        this.init = init;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
