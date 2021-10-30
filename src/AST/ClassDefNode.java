package AST;

import Util.Position;

import java.util.ArrayList;

public class ClassDefNode extends DefNode {
    public String id;
    public ArrayList<VarDefSubStmtNode> field;
    public ArrayList<ConstructorDefNode> constructor;
    public ArrayList<FuncDefNode> method;

    public ClassDefNode(String id, ArrayList<VarDefSubStmtNode> field,
                        ArrayList<ConstructorDefNode> constructor,
                        ArrayList<FuncDefNode> method, Position pos) {
        super(pos);
        this.id = id;
        this.field = field;
        this.constructor = constructor;
        this.method = method;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
