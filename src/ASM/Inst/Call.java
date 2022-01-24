package ASM.Inst;

import ASM.ASMBlock;
import ASM.ASMFunc;
import ASM.Operand.Reg;

/**
 *  call func
 *  call offset
 */
public class Call extends Inst{
	public ASMFunc dest;

	public Call(ASMFunc dest) {
		this.dest = dest;
	}

	@Override
	public String toString() {
		return String.format("call\t%s", dest);
	}
}
