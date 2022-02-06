package ASM;

import ASM.Operand.PhyReg;
import IR.Type.StructType;
import IR.Value.Constant.Constant;
import IR.Value.Global.Variable;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ASMRoot {
	public String filename;
	public ArrayList<ASMFunc> funcs = new ArrayList<>();
	public HashMap<String, Variable> gVar = null;
	public ArrayList<Constant> gConstant = null;

	public ASMRoot(String filename) {
		this.filename = filename;
	}
}
