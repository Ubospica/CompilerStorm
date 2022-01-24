package AST.Statement.ControlFlow;

import AST.ASTVisitor;
import AST.Expression.ExprNode;
import AST.Statement.StmtNode;
import Util.Position;

public class WhileStmtNode extends StmtNode {
	public ExprNode cond;
	public StmtNode body;

	public WhileStmtNode(ExprNode cond, StmtNode body, Position pos) {
		super(pos);
		this.cond = cond;
		this.body = body;
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}
}
