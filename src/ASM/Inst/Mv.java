package ASM.Inst;

import ASM.Operand.Operand;
import ASM.Operand.Reg;

/**
 *  mv rd, rs1
 */
public class Mv extends Inst {

	public Mv(Reg rd, Reg rs1) {
		this.rd = rd;
		this.rs1 = rs1;
	}

	@Override
	public String toString() {
		return String.format("mv\t%s, %s", rd, rs1);
	}
}
