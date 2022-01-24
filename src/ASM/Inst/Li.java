package ASM.Inst;

import ASM.Operand.Imm;
import ASM.Operand.Operand;
import ASM.Operand.Reg;
import Backend.ASMPrinter;

/**
 *  1.
 *   li rd, imm
 *  2.
 *   lui rd, %hi(imm)
 *   addi rd, rd, %lo(imm)
 */
public class Li extends Inst {
	public Reg rd;
	public Imm imm;
	public static final int base = 1 << 12;

	public Li(Reg rd, Imm imm) {
		this.rd = rd;
		this.imm = imm;
	}

	@Override
	public String toString() {
		if (imm.isLow()) {
			return String.format("li\t%s, %s", rd, imm);
		} else {
			return String.format("lui\t%s, %s", rd, imm.high()) +
					"\n" + ASMPrinter.indent +
					String.format("addi\t%s, %s, %s", rd, rd, imm.high());
		}
	}
}
