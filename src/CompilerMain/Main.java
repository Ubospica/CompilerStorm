package CompilerMain;

import AST.Definition.ProgramNode;
import AST.Scope.Scope;
import AST.Type.Type;
import Backend.IRPrinter;
import Builtin.BuiltinFunc;
import Frontend.ASTBuilder;
import Frontend.SemanticChecker;
import Frontend.SymbolCollector;
import Backend.IRBuilder;
import IR.Value.Use;
import Parser.MxLexer;
import Parser.MxParser;
import Util.Error.MxErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static java.lang.Math.max;


public class Main {
	public static void main(String[] args) throws Exception{
		String inputName = "a.mx";
		InputStream input = new FileInputStream(inputName);
		PrintStream output = new PrintStream("a.ll");

		// stdin & stdout
//		InputStream input = System.in;
//		PrintStream output = System.out;

		// parse; semantic; codegen
		int stage = 0;
		boolean chk = false;
		for (var i : args) {
			if (i.equals("-semantic")) {
				stage = max(stage, 2);
				chk = true;
			} else if (i.equals("-codegen")) {
				stage = max(stage, 3);
				chk = true;
			}
		}
		if (!chk) {
			stage = 4;
		}

		try {
			Scope globalScope = new Scope(null, BuiltinFunc.function);
			Type.globalScope = globalScope;

			// ANTLR lexer and parser calling
			MxLexer lexer = new MxLexer(CharStreams.fromStream(input));
			lexer.removeErrorListeners();
			lexer.addErrorListener(new MxErrorListener());
			MxParser parser = new MxParser(new CommonTokenStream(lexer));
			parser.removeErrorListeners();
			parser.addErrorListener(new MxErrorListener());
			ParseTree parseTreeRoot = parser.program();

			if (stage <= 1) return;

			// AST Building
			var astBuilder = new ASTBuilder();
			ProgramNode astRoot = (ProgramNode)astBuilder.visit(parseTreeRoot);

			// Semantic Checking
			new SymbolCollector(globalScope).visit(astRoot);
			new SemanticChecker(globalScope).visit(astRoot);

			if (stage <= 2) return;

			// IR Building and Printing
			var irBuilder = new IRBuilder("a.mx");
			irBuilder.visit(astRoot);
			var topModule = irBuilder.topModule;
			new IRPrinter(output).visit(topModule);

			if (stage <= 3) return;

//			mainFn f = new mainFn();
//			new IRBuilder(f, gScope).visit(astRoot);
//			// new IRPrinter(System.out).visitFn(f);
//
//			AsmFn asmF = new AsmFn();
//			new InstSelector(asmF).visitFn(f);
//			new RegAlloc(asmF).work();
//			new AsmPrinter(asmF, System.out).print();
		} catch (Error er) {
			System.err.println(er);
			throw new RuntimeException();
		}
	}
}
