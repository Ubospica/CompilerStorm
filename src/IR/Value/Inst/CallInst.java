package IR.Value.Inst;

import IR.Type.FuncType;
import IR.Type.Type;
import IR.Value.Value;

/**
 * call returnTy func (args)
 */
public class CallInst extends Inst {

	public CallInst(Value func) {
		super(((FuncType)func.type).returnType);
		this.addUse(func);
	}

	public CallInst(Value func, Value... args) {
		this(func);
		for (var v : args) {
			this.addUse(v);
		}
	}

}
