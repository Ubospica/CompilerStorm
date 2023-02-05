package IR.Value.Inst;

import IR.Type.Type;
import IR.Value.Global.BasicBlock;
import IR.Value.Value;

import java.util.ArrayList;
import java.util.Arrays;

import org.antlr.v4.runtime.misc.Pair;

public class PhiInst extends Inst {
	public ArrayList<Pair<Value, BasicBlock>> args = new ArrayList<>();
	public boolean isDomPhi = false;
	public Inst target = null;

	public PhiInst(Pair<Value, BasicBlock>... args) {
		super(args[0].a.type);
		this.args.addAll(Arrays.asList(args));
		for (var i : args) {
			addUse(i.a);
			addUse(i.b);
		}
	}

	public PhiInst(boolean isDomPhi, Inst target) {
		super(Type.VOID);
		this.isDomPhi = isDomPhi;
		this.target = target;
	}

	public void add(Value val, BasicBlock block) {
		this.args.add(new Pair<>(val, block));
		addUse(val);
		addUse(block);
	}
}
