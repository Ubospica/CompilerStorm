package ASM;

import ASM.Operand.PhyReg;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ASMRoot {
	public String filename;
	public ArrayList<ASMFunc> funcs = new ArrayList<>();

	public ASMRoot(String filename) {
		this.filename = filename;
	}
}
