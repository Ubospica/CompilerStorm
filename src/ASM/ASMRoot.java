package ASM;

import IR.Value.Constant.StrConstant;
import IR.Value.Global.Variable;

import java.util.ArrayList;
import java.util.HashMap;

public class ASMRoot {
	public String filename;
	public ArrayList<ASMFunc> funcs = new ArrayList<>();
	public HashMap<String, Variable> gVar = null;
	public ArrayList<StrConstant> gConstant = null;
	public int callArgStackDelta = 0;

	public ASMRoot(String filename) {
		this.filename = filename;
	}
}
