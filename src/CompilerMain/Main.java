package CompilerMain;

import AST.Definition.ProgramNode;
import AST.Scope.Scope;
import AST.Type.Type;
import Backend.ASMBuilder;
import Backend.ASMPrinter;
import Backend.IRBuilder;
import Backend.RegAllocator;
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

import java.io.InputStream;
import java.io.PrintStream;

import static java.lang.Math.max;


public class Main {
	public static void main(String[] args) throws Exception{
//		String inputName = "a.mx";
//		InputStream input = new FileInputStream(inputName);
//		PrintStream output = new PrintStream("a.ll");
//		PrintStream outputAsm = new PrintStream("a.s");
//		PrintStream outputAsmAlloc = new PrintStream("a.alloc.s");

		// stdin & stdout
		InputStream input = System.in;
		PrintStream output = System.out;

		// parse; semantic; codegen; optimize
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
			var irBuilder = new IRBuilder("a.mx", astRoot);
			var topModule = irBuilder.work();
//			new IRPrinter(output).visit(topModule);


			// Inst selection
			var builder = new ASMBuilder(topModule);
			var asmRoot = builder.work();
			// printing unallocated ASM
//			new ASMPrinter(outputAsm).visit(asmRoot);
			// reg alloc
			new RegAllocator(asmRoot).work();
			// printing ASM
//			new ASMPrinter(outputAsmAlloc).visit(asmRoot);
			new ASMPrinter(output).visit(asmRoot);

			if (stage <= 3) return;
		} catch (Error er) {
			System.err.println(er);
			throw new RuntimeException();
		}
	}
}
