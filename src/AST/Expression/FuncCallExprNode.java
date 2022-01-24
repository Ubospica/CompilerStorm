package AST.Expression;

import AST.ASTVisitor;
import Util.Position;

import java.util.ArrayList;

public class FuncCallExprNode extends ExprNode {
	public ExprNode func;
	public ArrayList<ExprNode> argList;

	public FuncCallExprNode(ExprNode func, ArrayList<ExprNode> argList, Position pos) {
		super(pos);
		this.func = func;
		this.argList = argList;
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}
}
