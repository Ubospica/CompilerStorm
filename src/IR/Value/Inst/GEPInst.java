package IR.Value.Inst;

import IR.Type.PointerType;
import IR.Type.StructType;
import IR.Type.Type;
import IR.Value.Constant.IntConstant;
import IR.Value.Use;
import IR.Value.Value;

/**
 * getelementptr operandTy, operandTy* pointer, {ty idx}*
 * return type: returnTy*
 */
public class GEPInst extends Inst {

	public GEPInst(Type resultTy, Value pointer, Value... idx) {
		super(resultTy);
		addUse(pointer);
		for (var i : idx) {
			addUse(i);
		}
	}

	// array pointer move
	public GEPInst(Value pointer, Value idx) {
		this(pointer.type, pointer, idx);
	}

	// struct find
	// pointer.type should be StructType
	public GEPInst(Value pointer, IntConstant idx1, IntConstant idx2) {
		this(new PointerType((
					((StructType)
					((PointerType)pointer.type).baseType)
					.varType).get((int)idx2.value)),
				pointer, idx1, idx2);
	}
}
