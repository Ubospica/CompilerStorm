package ASM.Inst;

import ASM.Operand.Imm;
import ASM.Operand.Reg;

/**
 *   li rd, imm
 */
public class Li extends Inst {
	public static final int base = 1 << 12;

	public Li(Reg rd, Imm imm) {
		this.rd = rd;
		this.imm = imm;
	}

	@Override
	public String toString() {
		return String.format("li\t%s, %s", rd, imm);
	}
}
