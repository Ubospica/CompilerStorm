package IR.Value.Inst;

import IR.Type.Type;
import IR.Value.Value;
import org.antlr.v4.runtime.misc.Pair;

public class PhiInst extends Inst {

	public PhiInst(Pair<Value, Value>... args) {
		super(args[0].a.type);
		for (var i : args) {
			addUse(i.a);
			addUse(i.b);
		}
	}

}
