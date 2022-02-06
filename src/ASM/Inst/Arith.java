package ASM.Inst;

import ASM.Operand.Operand;
import ASM.Operand.Reg;

/**
 *  add rd, rs1, rs2
 *  addi rd, rs, val
 */
public class Arith extends Inst {
	public String op;

	public Arith(String op, Reg rd, Reg rs1, Operand rs2) {
		this.op = op;
		this.rd = rd;
		this.rs1 = rs1;
		this.rs2 = rs2;
	}

	// one op: seqz rd, rs1
	public Arith(String op, Reg rd, Reg rs1) {
		this.op = op;
		this.rd = rd;
		this.rs1 = rs1;
		this.rs2 = null;
	}

	@Override
	public String toString() {
		if (rs2 != null) {
			return String.format("%s%s\t%s, %s, %s", op, (rs2 instanceof Reg ? "" : "i"), rd, rs1, rs2);
		} else {
			return String.format("%s\t%s, %s", op, rd, rs1);
		}
	}
}
