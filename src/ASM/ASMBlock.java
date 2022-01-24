package ASM;

import ASM.Inst.Inst;

import java.util.ArrayList;

public class ASMBlock {
	public String id;
	public ArrayList<Inst> insts = new ArrayList<>();


	public ASMBlock(String funcName, String blockName) {
		this.id = "." + funcName + "_" + blockName;
	}

	public ASMBlock(String funcName) {
		this(funcName, "tmp");
	}

	public void addInst(Inst inst) {
		insts.add(inst);
	}
}
