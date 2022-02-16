package Backend;


import ASM.ASMBlock;
import ASM.ASMFunc;
import ASM.ASMRoot;
import IR.Value.Constant.Constant;
import IR.Value.Constant.StrConstant;
import IR.Value.Global.Variable;

import java.io.PrintStream;

public class ASMPrinter {
	public PrintStream out;

	final private String indent = "\t";

//	private HashMap<String, AtomicInteger> regId = new HashMap<>();
	private int strId = 0;

	public ASMPrinter(PrintStream out) {
		this.out = out;
	}

	private void printIndent(String format, Object... args) {
		out.print(indent);
		out.printf(format, args);
		out.println();
	}

	public void visit(ASMRoot it) {
		printIndent(".text");
		printIndent(".file\t\"%s\"", it.filename);
		out.println();

		if (!it.funcs.isEmpty()) {
			it.funcs.forEach(x -> visit(x));
			out.println();
		}

		if (!it.gVar.isEmpty()) {
			printIndent(".section\t.sbss");
			it.gVar.forEach((k, v) -> visit(v));
			out.println();
		}

		if (!it.gConstant.isEmpty()) {
			printIndent(".section .rodata");
			it.gConstant.forEach(x -> visit(x));
			out.println();
		}
	}

	// there are only string constant
	// distinct name is given in irbuilder
	public void visit(Constant it) {
		var newIt = (StrConstant) it;
		out.printf("%s:\n", newIt.id);
		printIndent(".asciz\t\"%s\"", newIt.getAsmString());
	}

	public void visit(Variable it) {
		printIndent(".globl\t%s", it.id);
		out.printf("%s:\n", it.id);
		printIndent(".word\t0");
	}

	public void visit(ASMFunc it) {
		printIndent(".globl\t%s", it.id);
		out.printf("%s:\n", it.id);
		it.blocks.forEach(x -> visit(x));
		out.println();
	}

	public void visit(ASMBlock it) {
		out.printf("%s:\n", it.id);
		for (var x : it.insts) {
			printIndent("%s", x.toString()); // there may be '%' in the string
		}
	}
}
