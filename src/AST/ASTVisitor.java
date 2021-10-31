package AST;

import AST.Definition.*;
import AST.Expression.*;
import AST.Expression.Atom.*;
import AST.Statement.*;
import AST.Statement.ControlFlow.*;

/*
- ASTNode
  - ProgramNode		// node as a root
  - DefNode
    - FuncDefNode
    - ConstructorDefNode
    - ClassDefNode
  - StmtNode		// node as a statement
    - VarDefStmtNode
    - VarDefStmtSubNode
    - ReturnStmtNode
    - BlockStmtNode
    - ExprStmtNode
    - IfStmtNode
    - WhileStmtNode
    - ForStmtNode
    - ControlStmtNode
    - EmptyStmtNode
  - ExprNode		// node as an expression
    - AtomExprNode
      - IntExprNode
      - BoolExprNode
      - StringExprNode
      - NullExprNode
 */
public interface ASTVisitor {
    void visit(ProgramNode it);
    void visit(FuncDefNode it);
    void visit(ConstructorDefNode it);
    void visit(ClassDefNode it);
    void visit(VarDefStmtNode it);
    void visit(VarDefSubStmtNode it);
    void visit(ReturnStmtNode it);
    void visit(BlockStmtNode it);
    void visit(ExprStmtNode it);
    void visit(IfStmtNode it);
    void visit(WhileStmtNode it);
    void visit(ForStmtNode it);
    void visit(ControlStmtNode it);
    void visit(EmptyStmtNode it);
    void visit(IntExprNode it);
    void visit(BoolExprNode it);
    void visit(StringExprNode it);
    void visit(NullExprNode it);
    void visit(BinaryExprNode it);
    void visit(PrefixExprNode it);
    void visit(SuffixExprNode it);
    void visit(LambdaExprNode it);
    void visit(MemberExprNode it);
    void visit(FuncCallExprNode it);
    void visit(SubscriptExprNode it);
    void visit(NewExprNode it);
    void visit(ThisExprNode it);
    void visit(VarExprNode it);
    void visit(TypeNode it);
}
