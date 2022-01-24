package AST.Statement.ControlFlow;

import AST.ASTVisitor;
import AST.Expression.ExprNode;
import AST.Statement.StmtNode;
import Util.Position;

public class ReturnStmtNode extends StmtNode {
	public ExprNode value;
	public ReturnStmtNode(ExprNode value, Position pos) {
		super(pos);
		this.value = value;
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}
}
