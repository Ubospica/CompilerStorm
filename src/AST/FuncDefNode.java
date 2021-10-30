package AST;

import Util.Position;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;

public class FuncDefNode extends DefNode {
    public TypeNode returnType;
    public String Identifier;
    public ArrayList<VarDefSubStmtNode> paramList;
    public BlockStmtNode body;

    public FuncDefNode(TypeNode type, String id, ArrayList<VarDefSubStmtNode> param, BlockStmtNode body, Position pos) {
        super(pos);
        this.returnType = type;
        this.Identifier = id;
        this.paramList = param;
        this.body = body;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
