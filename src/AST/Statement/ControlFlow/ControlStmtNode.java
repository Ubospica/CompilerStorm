package AST.Statement.ControlFlow;

import AST.ASTVisitor;
import AST.Statement.StmtNode;
import Util.Position;

public class ControlStmtNode extends StmtNode {
	public enum Type {
		BREAK, CONTINUE
	}
	public Type type;

	public ControlStmtNode(Type type, Position pos) {
		super(pos);
		this.type = type;
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}
}
