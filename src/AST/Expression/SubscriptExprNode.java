package AST.Expression;

import AST.ASTVisitor;
import Util.Position;

public class SubscriptExprNode extends ExprNode {
	public ExprNode base, index;

	public SubscriptExprNode(ExprNode base, ExprNode index, Position pos) {
		super(pos);
		this.base = base;
		this.index = index;
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}
}
