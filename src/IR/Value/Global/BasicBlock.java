package IR.Value.Global;

import IR.Pass;
import IR.Type.Type;
import IR.Value.Inst.Inst;
import IR.Value.Value;

import java.util.LinkedList;

public class BasicBlock extends Value {
	public LinkedList<Inst> insts = new LinkedList<>();
	public LinkedList<BasicBlock> pre = new LinkedList<>(), nxt = new LinkedList<>();

	public BasicBlock() {
		super(Type.LABEL);
	}

	public BasicBlock(String id) {
		super(Type.LABEL);
		this.id = id;
	}

	public void addInst(Inst inst) {
		insts.add(inst);
	}

	public void accept(Pass pass) {
		pass.visit(this);
	}

	public static void addLink(BasicBlock from, BasicBlock to) {
		from.nxt.add(to);
		to.pre.add(from);
	}

}
