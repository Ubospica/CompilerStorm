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
	public Reg rd;
	public Operand val;

	public Lui(Reg rd, Operand val) {
		this.rd = rd;
		this.val = val;
	}

	@Override
	public String toString() {
		if (val instanceof Symbol sVal) {
			return String.format("lui %s, %s", rd, sVal.hi());
		} else {
			return String.format("lui %s, %s", rd, val);
		}
	}
}
