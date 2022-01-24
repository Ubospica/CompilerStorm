package AST.Expression;


import AST.ASTVisitor;
import Util.Position;

public class AssignExprNode extends ExprNode {
	public ExprNode left, right;

	public AssignExprNode(ExprNode left, ExprNode right, Position pos) {
		super(pos);
		this.left = left;
		this.right = right;
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}
}
