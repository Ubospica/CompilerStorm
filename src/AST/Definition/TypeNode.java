package AST.Definition;

import AST.ASTNode;
import AST.ASTVisitor;
import AST.TypeEnum;
import Util.Position;

public class TypeNode extends ASTNode {


    public TypeEnum type;
    public String identifier;
    public int dim;

    public TypeNode(TypeEnum type, String identifier, int dim, Position pos) {
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
