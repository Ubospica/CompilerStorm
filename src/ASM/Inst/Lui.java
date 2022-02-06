package ASM.Inst;

import ASM.Operand.Imm;
import ASM.Operand.Operand;
import ASM.Operand.Reg;
import ASM.Operand.Symbol;
import Backend.ASMPrinter;

/**
 *  lui rd, %hi(symbol)
 *  lui rd, imm
 */
public class Lui extends Inst {

	public Lui(Reg rd, Operand imm) {
		this.rd = rd;
		this.imm = imm;
	}

	@Override
	public String toString() {
		if (imm instanceof Symbol sVal) {
			return String.format("lui %s, %s", rd, sVal.hi());
		} else {
			return String.format("lui %s, %s", rd, imm);
		}
	}
}
