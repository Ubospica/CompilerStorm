package IR.Value;

import IR.Pass;
import IR.Type.Type;

import java.util.LinkedList;


/**
 *  Everything in the LLVM IR is a **Value**
 */
abstract public class Value {

	public Type type;
	public String id = "";
	public String printId = "";
	public LinkedList<Use> useList = new LinkedList<>();

	public Value(Type type) {
		this.type = type;
	}

	public abstract void accept(Pass pass);

}
