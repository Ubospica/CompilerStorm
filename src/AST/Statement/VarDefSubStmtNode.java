package AST.Statement;

import AST.ASTVisitor;
import AST.Expression.ExprNode;
import AST.Definition.TypeNode;
import Util.Position;

public class VarDefSubStmtNode extends StmtNode {
	public TypeNode type;
	public String id;
	public ExprNode init;
	public boolean isGlobal = false;

	//'type' field is assigned by caller
	public VarDefSubStmtNode(String id, ExprNode init, Position pos) {
		super(pos);
		this.id = id;
		this.init = init;
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}
}
