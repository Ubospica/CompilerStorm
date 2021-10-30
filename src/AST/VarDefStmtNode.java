package AST;

import Util.Position;

import java.util.ArrayList;

public class VarDefStmtNode extends StmtNode {
    public ArrayList<VarDefSubStmtNode> varList = new ArrayList<>();

    public VarDefStmtNode(ArrayList<VarDefSubStmtNode> varList, Position pos) {
        super(pos);
        this.varList = varList;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
