package AST;

import Util.Position;

public class TypeNode extends ASTNode {

    public enum Types {
        VOID, INT, BOOL, STRING, CLASS
    }
    Types type;
    String identifier;
    int dim;

    public TypeNode(Types type, String identifier, int dim, Position pos) {
        super(pos);
        this.type = type;
        this.identifier = identifier;
        this.dim = dim;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
