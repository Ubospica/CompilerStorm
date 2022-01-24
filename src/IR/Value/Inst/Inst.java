package IR.Value.Inst;

import IR.Pass;
import IR.Type.Type;
import IR.Value.User;

/**
 * Instruction Hierarchy:
 * 1. Computing inst:
 *	BinaryInst, IcmpInst
 * 2. Control flow inst:
 *	RetInst, BrInst
 * 3. Memory operation inst:
 *	AllocaInst, LoadInst, StoreInst
 * 4. Other inst:
 *	CallInst, GEPInst, BitcastInst
 */
abstract public class Inst extends User {

	public Inst(Type type) {
		super(type);
	}


	public void accept(Pass pass) {
		pass.visit(this);
	}

}
