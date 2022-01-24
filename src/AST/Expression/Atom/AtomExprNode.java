package AST.Expression.Atom;

import AST.Expression.ExprNode;
import Util.Position;

public abstract class AtomExprNode extends ExprNode {
	public AtomExprNode(Position pos) {
		super(pos);
	}
}
