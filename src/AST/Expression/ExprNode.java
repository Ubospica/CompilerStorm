package AST.Expression;

import AST.ASTNode;
import AST.Type.Type;
import IR.Value.Value;
import Util.Position;

public abstract class ExprNode extends ASTNode {
	public Type type;
	public boolean assignable = false;

	public Value irValue = null;
	public Value irPointer = null;

	public ExprNode(Position pos) {
		super(pos);
	}
}
