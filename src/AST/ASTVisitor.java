package AST;

import AST.Definition.*;
import AST.Expression.*;
import AST.Expression.Atom.*;
import AST.Statement.*;
import AST.Statement.ControlFlow.*;


public interface ASTVisitor {
	default void visit(ProgramNode it) { }
	default void visit(FuncDefNode it) { }
	default void visit(ConstructorDefNode it) { }
	default void visit(ClassDefNode it) { }
	default void visit(VarDefStmtNode it) { }
	default void visit(VarDefSubStmtNode it) { }
	default void visit(ReturnStmtNode it) { }
	default void visit(BlockStmtNode it) { }
	default void visit(ExprStmtNode it) { }
	default void visit(IfStmtNode it) { }
	default void visit(WhileStmtNode it) { }
	default void visit(ForStmtNode it) { }
	default void visit(ControlStmtNode it) { }
	default void visit(EmptyStmtNode it) { }
	default void visit(IntExprNode it) { }
	default void visit(BoolExprNode it) { }
	default void visit(StringExprNode it) { }
	default void visit(NullExprNode it) { }
	default void visit(BinaryExprNode it) { }
	default void visit(BinaryBoolExprNode it) { }
	default void visit(AssignExprNode it) { }
	default void visit(PrefixExprNode it) { }
	default void visit(SuffixExprNode it) { }
	default void visit(LambdaExprNode it) { }
	default void visit(MemberExprNode it) { }
	default void visit(FuncCallExprNode it) { }
	default void visit(SubscriptExprNode it) { }
	default void visit(NewExprNode it) { }
	default void visit(ThisExprNode it) { }
	default void visit(VarExprNode it) { }
	default void visit(TypeNode it) { }
}
