package CompilerMain;

import AST.Definition.ProgramNode;
import AST.Scope.Scope;
import AST.Scope.Type.Type;
import Builtin.BuiltinFunc;
import Frontend.ASTBuilder;
import Frontend.SemanticChecker;
import Frontend.SymbolCollector;
import Parser.MxLexer;
import Parser.MxParser;
import Util.Error.MxErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;


public class Main {
    public static void main(String[] args) throws Exception{

//        String name = "test.mx";
//        InputStream input = new FileInputStream(name);
        InputStream input = System.in;

        try {
            ProgramNode ASTRoot;
            Scope globalScope = new Scope(null, BuiltinFunc.function);
            Type.globalScope = globalScope;

            MxLexer lexer = new MxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxErrorListener());
            MxParser parser = new MxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxErrorListener());
            ParseTree parseTreeRoot = parser.program();
            ASTBuilder astBuilder = new ASTBuilder();
            ASTRoot = (ProgramNode)astBuilder.visit(parseTreeRoot);
            new SymbolCollector(globalScope).visit(ASTRoot);
            new SemanticChecker(globalScope).visit(ASTRoot);

//            mainFn f = new mainFn();
//            new IRBuilder(f, gScope).visit(ASTRoot);
//            // new IRPrinter(System.out).visitFn(f);
//
//            AsmFn asmF = new AsmFn();
//            new InstSelector(asmF).visitFn(f);
//            new RegAlloc(asmF).work();
//            new AsmPrinter(asmF, System.out).print();
        } catch (Error er) {
            System.err.println(er.toString());
            throw new RuntimeException();
        }
    }
}