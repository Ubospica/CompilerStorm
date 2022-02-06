package Backend;


import ASM.ASMBlock;
import ASM.ASMFunc;
import ASM.ASMRoot;
import ASM.Operand.VirtualReg;
import IR.Pass;
import IR.Type.FuncType;
import IR.Type.PointerType;
import IR.Type.StructType;
import IR.Value.Constant.*;
import IR.Value.Global.BasicBlock;
import IR.Value.Global.Function;
import IR.Value.Global.Module;
import IR.Value.Global.Variable;
import IR.Value.Inst.*;
import IR.Value.Value;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
		out.print("\n");
	}

	public void visit(ASMRoot it) {
		giveName(it);
		printIndent(".text");
		printIndent(".file\t\"%s\"", it.filename);
		it.funcs.forEach(x -> visit(x));
		out.print("\n");
		printIndent(".section\t.sbss");
		it.gVar.forEach((k, v) -> visit(v));
		out.print("\n");
		printIndent(".section .rodata");
		it.gConstant.forEach(x -> visit(x));
		out.print("\n");
	}

	void giveName(ASMRoot it) {
		it.funcs.forEach(x -> {
			int regCnt = 0;
			int blockCnt = 0;
			for (var y : x.blocks) {
				y.id = y.id + (blockCnt++);
				for (var z : y.insts) {
					if (z.rd instanceof VirtualReg && z.rd.id.equals("tmp")) {
						z.rd.id = z.rd.id + (regCnt++);
					}
					if (z.rs1 instanceof VirtualReg && z.rs1.id.equals("tmp")) {
						z.rs1.id = z.rs1.id + (regCnt++);
					}
					if (z.rs2 instanceof VirtualReg reg && reg.id.equals("tmp")) {
						reg.id = reg.id + (regCnt++);
					}
				}
			}
		});
		int cnt = 0;
		for (var x : it.gConstant) {
			x.id = x.id + (cnt++);
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
	}

	public void visit(ASMBlock it) {
		out.printf("%s:\n", it.id);
		it.insts.forEach(x -> printIndent(x.toString()));
	}
}
