package IR.Value.Global;

import IR.Pass;
import IR.Type.Type;
import IR.Value.Value;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * global function.
 * function must have name.
 *
 */
public class Function extends Value {
	public ArrayList<Variable> arg = new ArrayList<>();
	// blocks.empty: declare function
	public LinkedList<BasicBlock> blocks = new LinkedList<>();

	public Function(Type type, String id) {
		super(type);
		this.id = id;
	}

	public void accept(Pass pass) {
		pass.visit(this);
	}

}
