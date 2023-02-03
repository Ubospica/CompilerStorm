import AST.Definition.ProgramNode;
import AST.Scope.Scope;
import AST.Type.Type;
import Backend.*;
import Builtin.BuiltinFunc;
import Frontend.ASTBuilder;
import Frontend.SemanticChecker;
import Frontend.SymbolCollector;
import Parser.MxLexer;
import Parser.MxParser;
import Util.Error.MxErrorListener;
import Util.BuiltinFunctionASMPrinter;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Objects;


public class Compiler {
	public static void main(String[] args) throws Exception{
		InputStream input;
		PrintStream output, outputIR, outputAsm;

		var argsList = Arrays.asList(args);

		if (argsList.contains("-debug")) {
			System.out.println("debug mode");
			String inputName = "a.mx";
			input = new FileInputStream(inputName);
			outputIR = new PrintStream("a.ll");
			outputAsm = new PrintStream("a.s");
			output = new PrintStream("output.s");
		} else {
			input = System.in;
			output = new PrintStream("output.s");
			outputIR = outputAsm = System.err;
		}

		// parse; semantic; codegen; optimize
		int stage = 0;

		if (argsList.contains("-parse")) {
			stage = 1;
		} if (argsList.contains("-semantic")) {
			stage = 2;
		} else if (argsList.contains("-codegen")) {
			stage = 3;
		} else {
			stage = 3;
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

			if (stage == 1) return;

			// AST Building
			var astBuilder = new ASTBuilder();
			ProgramNode astRoot = (ProgramNode)astBuilder.visit(parseTreeRoot);

			// Semantic Checking
			new SymbolCollector(globalScope).visit(astRoot);
			new SemanticChecker(globalScope).visit(astRoot);

			if (stage == 2) return;

			// IR Building and Printing
			var irBuilder = new IRBuilder("a.mx", astRoot);
			var topModule = irBuilder.work();
			// new IRPrinter(outputIR).visit(topModule);

			// Inst selection
			var builder = new ASMBuilder(topModule);
			var asmRoot = builder.work();
			// printing unallocated ASM
			// new ASMPrinter(outputAsm).visit(asmRoot);
			// reg alloc
			new RegAllocator(asmRoot).work();
			// printing ASM
			new ASMPrinter(output).visit(asmRoot);

			// output builtin
			new BuiltinFunctionASMPrinter("builtin.s");

			if (stage == 3) return;
		} catch (Error er) {
			System.err.println(er);
			throw new RuntimeException();
		}
	}
}
