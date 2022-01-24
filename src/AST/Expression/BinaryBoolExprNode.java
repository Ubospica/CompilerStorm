package AST.Expression;

import AST.ASTVisitor;
import Util.Position;

public class BinaryBoolExprNode extends BinaryExprNode{
	public BinaryBoolExprNode(ExprNode left, ExprNode right, String op, Position pos) {
		super(left, right, op, pos);
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}
}
