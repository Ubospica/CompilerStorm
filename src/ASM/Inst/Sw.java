package ASM.Inst;

import ASM.Operand.Imm;
import ASM.Operand.Reg;

/**
 *  sw rs2, offset(rs1)
 */
public class Sw extends Inst {
	public Reg rs2, rs1;
	public Imm offset;

	public Sw(Reg rs2, Reg rs1, Imm offset) {
		this.rs2 = rs2;
		this.rs1 = rs1;
		this.offset = offset;
	}

	@Override
	public String toString() {
		return String.format("sw\t%s, %s(%s)", rs2, offset, rs1);
	}
}
