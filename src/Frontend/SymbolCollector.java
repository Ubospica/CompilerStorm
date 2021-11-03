package Frontend;

import AST.ASTVisitor;
import AST.Definition.*;
import AST.Expression.*;
import AST.Expression.Atom.*;
import AST.Scope.Scope;
import AST.Scope.Type.ClassType;
import AST.Scope.Type.FuncType;
import AST.Scope.Type.Type;
import AST.Statement.*;
import AST.Statement.ControlFlow.*;
import AST.TypeEnum;
import Util.Error.SemanticError;
import Util.Position;

import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;


// Collects:
// 1. class, member field, methods
// 2. global func and type

// Checks:
// 1. in class var, all func: type defined
// 2. in class var, all func, class: redefine
// 3. init value of func parameter & class field

public class SymbolCollector implements ASTVisitor {
    private Scope globalScope;

    private boolean inClass = false;
    private ClassType curClass;

    public SymbolCollector(Scope globalScope) {
        this.globalScope = globalScope;
        //basic types
//        globalScope.addType("int", new Type(TypeEnum.INT), new Position());
//        globalScope.addType("String", new Type(TypeEnum.STRING), new Position());
//        globalScope.addType("bool", new Type(TypeEnum.BOOL), new Position());
    }

    @Override
    public void visit(ProgramNode it) {
        for (var i : it.body) {
            if (i instanceof ClassDefNode) {
                globalScope.addType(((ClassDefNode) i).id, new ClassType(((ClassDefNode) i).id), i.pos);
            }
        }
        for (var i : it.body) {
            i.accept(this);
        }
    }


    @Override
    public void visit(FuncDefNode it) {
//        if (it.id.equals("printme")) {
//            int tmp = 1;
//        }
        Type returnType = Type.transNode(it.returnType);
        if (returnType.type != TypeEnum.VOID) {
            globalScope.checkType(returnType, it.returnType.pos);
        }
        ArrayList<Type> param = new ArrayList<>();
        for (var i : it.paramList) {
//            if (i.init != null) {
//                throw new SemanticError("Function argument with init value: " + i.id, i.pos);
//            }
            Type paramType = Type.transNode(i.type);
            globalScope.checkType(paramType, i.type.pos);
            param.add(paramType);
        }
        var func = new FuncType(it.id, returnType, param);

        if (inClass) {
            curClass.addFunc(it.id, func, it.pos);
        } else { //global
            globalScope.addFunc(it.id, func, it.pos);
        }
    }

    @Override
    public void visit(ClassDefNode it) {
//        if (it.id.equals("Test")) {
//            int tmp = 1;
//        }
        ClassType classType = globalScope.getType(it.id, it.pos);
        for (var i : it.field) {
//            if (i.init != null) {
//                throw new SemanticError("Class field with init: " + i.id, i.pos);
//            }
            Type fieldType = Type.transNode(i.type);
            globalScope.checkType(fieldType, i.type.pos);
            classType.addVar(i.id, fieldType, i.pos);
        }
        if (!it.constructor.isEmpty()) {
            classType.constructor = new FuncType(classType.name, null, new ArrayList<>());
        }
        inClass = true;
        curClass = classType;
        for (var i : it.method) {
            i.accept(this);
        }
        inClass = false;
    }

    @Override
    public void visit(ConstructorDefNode it) { }


    @Override
    public void visit(VarDefStmtNode it) { }

    @Override
    public void visit(VarDefSubStmtNode it) { }

    @Override
    public void visit(ReturnStmtNode it) { }

    @Override
    public void visit(BlockStmtNode it) { }

    @Override
    public void visit(ExprStmtNode it) { }

    @Override
    public void visit(IfStmtNode it) { }

    @Override
    public void visit(WhileStmtNode it) { }

    @Override
    public void visit(ForStmtNode it) { }

    @Override
    public void visit(ControlStmtNode it) { }

    @Override
    public void visit(EmptyStmtNode it) { }

    @Override
    public void visit(IntExprNode it) { }

    @Override
    public void visit(BoolExprNode it) { }

    @Override
    public void visit(StringExprNode it) { }

    @Override
    public void visit(NullExprNode it) { }

    @Override
    public void visit(BinaryExprNode it) { }

    @Override
    public void visit(PrefixExprNode it) { }

    @Override
    public void visit(SuffixExprNode it) { }

    @Override
    public void visit(LambdaExprNode it) { }

    @Override
    public void visit(MemberExprNode it) { }

    @Override
    public void visit(FuncCallExprNode it) { }

    @Override
    public void visit(SubscriptExprNode it) { }

    @Override
    public void visit(NewExprNode it) { }

    @Override
    public void visit(ThisExprNode it) { }

    @Override
    public void visit(VarExprNode it) { }

    @Override
    public void visit(TypeNode it) { }
}
