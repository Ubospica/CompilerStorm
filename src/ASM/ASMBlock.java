package ASM;

import ASM.Inst.*;
import ASM.Operand.Symbol;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class ASMBlock {
	public String id;
	public LinkedList<Inst> insts = new LinkedList<>();

	public ArrayList<ASMBlock> pre = new ArrayList<>(), nxt = new ArrayList<>();

	public static ListIterator<Inst> insertInstIterator = null;

	public ASMBlock(String funcName, String blockName) {
		this.id = "." + funcName + "_" + blockName;
	}

	public ASMBlock(String funcName) {
		this(funcName, "tmp");
	}

	public void addInst(Inst inst) {
		// add block link
		if (inst instanceof J newInst) {
			addLink(this, newInst.dest);
		} else if (inst instanceof Beqz newInst) {
			addLink(this, newInst.dest);
		} else if (inst instanceof Sw && inst.imm instanceof Symbol) {
			// sw rs2, symbol, rs1  ---->
			// auipc rs1, %hi(symbol)
			// sw rs2, %lo(symbol)(rs1)
			var inst1 = new Lui(inst.rs1, inst.imm);
			if (insertInstIterator == null){
				insts.add(inst1);
			} else {
				insertInstIterator.add(inst1);
			}
		}

		if (insertInstIterator == null){
			insts.add(inst);
		} else {
			insertInstIterator.add(inst);
		}
	}

	public static void addLink(ASMBlock from, ASMBlock to) {
		from.nxt.add(to);
		to.pre.add(from);
	}

	public String toString() {
		return id;
	}
}
