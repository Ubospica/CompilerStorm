package IR.Value.Inst;

import IR.Type.Type;
import IR.Value.Use;
import IR.Value.Value;

/**
 * store ty val, ty* pointer
 */
public class StoreInst extends Inst {

	public StoreInst(Value value, Value pointer) {
		super(Type.VOID);
		addUse(value);
		addUse(pointer);
	}

}
