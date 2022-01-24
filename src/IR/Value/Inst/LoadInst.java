package IR.Value.Inst;

import IR.Type.PointerType;
import IR.Value.*;

/**
 * load type, type* pointer
 */
public class LoadInst extends Inst {

	public LoadInst(Value pointer) {
		super(((PointerType)pointer.type).getDereference());
		addUse(pointer);
	}

}
