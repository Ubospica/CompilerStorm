package AST;

import Util.Position;

public abstract class ASTNode {
    Position pos;

    public ASTNode(Position pos) {
        this.pos = pos;
    }

    public abstract void accept(ASTVisitor visitor); //?
}
