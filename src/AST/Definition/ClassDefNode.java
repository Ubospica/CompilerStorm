package AST.Definition;

import AST.ASTVisitor;
import AST.Scope.Scope;
import AST.Statement.VarDefSubStmtNode;
import Util.Position;

import java.util.ArrayList;

public class ClassDefNode extends AST.Definition.DefNode {
	public String id;
	public ArrayList<VarDefSubStmtNode> field;
	public ArrayList<ConstructorDefNode> constructor;
	public ArrayList<FuncDefNode> method;

//	public Scope scope = null;

	public ClassDefNode(String id, ArrayList<VarDefSubStmtNode> field,
	                    ArrayList<ConstructorDefNode> constructor,
	                    ArrayList<FuncDefNode> method, Position pos) {
		super(pos);
		this.id = id;
		this.field = field;
		this.constructor = constructor;
		this.method = method;
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}
}
