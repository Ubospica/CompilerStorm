package IR.Value.Inst;

import IR.Type.Type;
import IR.Value.Global.BasicBlock;
import IR.Value.Value;
import org.antlr.v4.runtime.misc.Pair;

public class PhiInst extends Inst {
	public Pair<Value, BasicBlock>[] args;

	public PhiInst(Pair<Value, BasicBlock>... args) {
		super(args[0].a.type);
		this.args = args;
		for (var i : args) {
			addUse(i.a);
			addUse(i.b);
		}
	}

}
