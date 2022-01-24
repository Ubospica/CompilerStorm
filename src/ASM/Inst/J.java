package ASM.Inst;

import ASM.ASMBlock;
import ASM.Operand.Reg;

/**
 *  j offset
 *  j block
 */
public class J extends Inst {
	public ASMBlock dest;

	public J(ASMBlock dest) {
		this.dest = dest;
	}

	@Override
	public String toString() {
		return String.format("j\t%s", dest);
	}
}
