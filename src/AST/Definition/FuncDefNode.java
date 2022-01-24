package AST.Definition;

import AST.*;
import AST.Scope.Scope;
import AST.Statement.BlockStmtNode;
import AST.Statement.VarDefSubStmtNode;
import AST.Type.Type;
import IR.Value.Global.Function;
import Util.Position;

import java.util.ArrayList;

public class FuncDefNode extends DefNode {
	public TypeNode returnType;
	public String id;
	public ArrayList<VarDefSubStmtNode> paramList;
	public BlockStmtNode body;

	public FuncDefNode(TypeNode type, String id, ArrayList<VarDefSubStmtNode> param, BlockStmtNode body, Position pos) {
		super(pos);
		this.returnType = type;
		this.id = id;
		this.paramList = param;
		this.body = body;
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}
}
