package AST.Expression.Atom;

import AST.ASTVisitor;
import Util.Position;

public class BoolExprNode extends AtomExprNode {
	public boolean value;

	public BoolExprNode(boolean value, Position pos) {
		super(pos);
		this.value = value;
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}
}
