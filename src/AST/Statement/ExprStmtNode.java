package AST.Statement;

import AST.ASTVisitor;
import AST.Expression.ExprNode;
import Util.Position;

public class ExprStmtNode extends StmtNode {
	public ExprNode expr;

	public ExprStmtNode(ExprNode expr, Position pos) {
		super(pos);
		this.expr = expr;
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}
}
