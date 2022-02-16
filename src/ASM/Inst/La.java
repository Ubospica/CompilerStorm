package ASM.Inst;

import ASM.Operand.Reg;
import ASM.Operand.Symbol;

// lla rd, symbol
public class La extends Inst {

	public La(Reg rd, Symbol symbol) {
		this.rd = rd;
		this.imm = symbol;
	}

	@Override
	public String toString() {
		return String.format("la\t%s, %s", rd, imm);
	}
}
