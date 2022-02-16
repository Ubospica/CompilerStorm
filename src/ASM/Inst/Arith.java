package ASM.Inst;

import ASM.Operand.Imm;
import ASM.Operand.Reg;

/**
 *  add rd, rs1, rs2
 *  addi rd, rs, imm
 */
public class Arith extends Inst {
	public String op;

	public Arith(String op, Reg rd, Reg rs1, Reg rs2) {
		this.op = op;
		this.rd = rd;
		this.rs1 = rs1;
		this.rs2 = rs2;
	}

	public Arith(String op, Reg rd, Reg rs1, Imm imm) {
		this.op = op;
		this.rd = rd;
		this.rs1 = rs1;
		this.imm = imm;
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
		if (imm != null) { // addi rd, rs, imm
			return String.format("%si\t%s, %s, %s", op, rd, rs1, imm);
		} else if (rs2 != null) { // add rd, rs1, rs2
			return String.format("%s\t%s, %s, %s", op, rd, rs1, rs2);
		} else { // seqz rd, rs1
			return String.format("%s\t%s, %s", op, rd, rs1);
		}
	}
}
