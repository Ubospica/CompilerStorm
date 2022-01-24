package ASM.Inst;

import ASM.Operand.Imm;
import ASM.Operand.Reg;


/**
 *  lw rd, offset(rs1)
 */
public class Lw extends Inst {
	public Reg rd, rs1;
	public Imm offset;

	public Lw(Reg rd, Reg rs1, Imm offset) {
		this.rd = rd;
		this.rs1 = rs1;
		this.offset = offset;
	}

	@Override
	public String toString() {
		return String.format("lw\t%s, %s(%s)", rd, offset, rs1);
	}
}
