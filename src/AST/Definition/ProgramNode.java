package AST.Definition;

import AST.ASTNode;
import AST.ASTVisitor;
import Util.Position;

import java.util.ArrayList;

public class ProgramNode extends ASTNode {
	public ArrayList<ASTNode> body = new ArrayList<>();
	public ProgramNode(Position pos) {
		super(pos);
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}
}
