package AST.Expression;

import AST.ASTVisitor;
import AST.Definition.TypeNode;
import Util.Position;

import java.util.ArrayList;

// new array dimension: newType.dim

public class NewExprNode extends ExprNode {
	public TypeNode newType;
	public ArrayList<ExprNode> sizes;

	public NewExprNode(TypeNode newType, ArrayList<ExprNode> sizes, Position pos) {
		super(pos);
		this.newType = newType;
		this.sizes = sizes;
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}
}
