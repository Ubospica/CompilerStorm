package ASM;


import ASM.Operand.PhyReg;
import ASM.Operand.VirtualReg;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.LinkedList;

public class ASMFunc {
	public String id;
	public LinkedList<ASMBlock> blocks = new LinkedList<>();
	public int argSize;

	public ArrayList<Pair<PhyReg, VirtualReg>> savedRegs = new ArrayList<>();

	public ASMFunc(String id, int argSize) {
		this.id = id;
		this.argSize = argSize;
	}

	public String toString() {
		return id;
	}
}
