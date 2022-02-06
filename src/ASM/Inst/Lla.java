package ASM.Inst;

import ASM.Operand.Operand;
import ASM.Operand.Reg;
import ASM.Operand.Symbol;

/**
 *  add rd, rs1, rs2
 *  addi rd, rs, val
 */
public class Lla extends Inst {

	public Lla(Reg rd, Symbol symbol) {
		this.rd = rd;
		this.imm = symbol;
	}

	@Override
	public String toString() {
		return String.format("lla\t%s, %s", rd, imm);
	}
}
