package ASM.Inst;

import ASM.Operand.Operand;
import ASM.Operand.Reg;

/**
 *  add rd, rs1, rs2
 *  addi rd, rs, val
 */
public class Arith extends Inst {
	public String op;
	public Reg rd, rs1;
	public Operand rs2;

	public Arith(String op, Reg rd, Reg rs1, Operand rs2) {
		this.rd = rd;
		this.rs1 = rs1;
		this.rs2 = rs2;
	}

	@Override
	public String toString() {
		return String.format("%s%s\t%s, %s, %s", op, (rs2 instanceof Reg ? "" : "i"), rd, rs1, rs2);
	}
}
