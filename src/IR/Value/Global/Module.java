package IR.Value.Global;

import IR.Pass;
import IR.Type.StructType;
import IR.Type.Type;
import IR.Value.Constant.StrConstant;
import IR.Value.Value;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * stands for the whole file
 */
public class Module extends Value {
	public String filename;
	public HashMap<String, Function> gFunc = new HashMap<>();
	public HashMap<String, Variable> gVar = new HashMap<>();
	public HashMap<String, StructType> gStruct = new HashMap<>();
	public ArrayList<StrConstant> gConstant = new ArrayList<>();

//	public BasicBlock initBlock = new BasicBlock("func.entry");

	public Module(String filename) {
		super(Type.VOID);
		this.filename = filename;
	}


	public void accept(Pass pass) {
		pass.visit(this);
	}

}
