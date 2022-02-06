package ASM;


import ASM.Operand.PhyReg;
import ASM.Operand.VirtualReg;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class ASMFunc {
	public String id;
	public ArrayList<ASMBlock> blocks = new ArrayList<>();

	public ArrayList<Pair<PhyReg, VirtualReg>> savedRegs = new ArrayList<>();

	public ASMFunc(String id) {
		this.id = id;
	}

	public String toString() {
		return id;
	}
}
