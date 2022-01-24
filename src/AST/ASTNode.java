package AST;

import Util.Position;

public abstract class ASTNode {
	public Position pos;

	public ASTNode(Position pos) {
		this.pos = pos;
	}

	public abstract void accept(AST.ASTVisitor visitor);
}
