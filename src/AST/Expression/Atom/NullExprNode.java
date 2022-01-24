package AST.Expression.Atom;

import AST.ASTVisitor;
import Util.Position;

public class NullExprNode extends AtomExprNode {
	public NullExprNode(Position pos) {
		super(pos);
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}
}
