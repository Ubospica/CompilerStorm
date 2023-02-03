package AST.Statement.ControlFlow;

import AST.ASTVisitor;
import AST.Expression.ExprNode;
import AST.Statement.StmtNode;
import AST.Statement.VarDefSubStmtNode;
import Util.Position;

import java.util.ArrayList;

public class ForStmtNode extends StmtNode {
	public ExprNode init, cond, incr;
	public StmtNode body;
	public ArrayList<VarDefSubStmtNode> initDef;

	public ForStmtNode(ExprNode init, ArrayList<VarDefSubStmtNode> initDef, ExprNode cond, ExprNode incr, StmtNode body, Position pos) {
		super(pos);
		this.init = init;
		this.initDef = initDef;
		this.cond = cond;
		this.incr = incr;
		this.body = body;
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}
}
