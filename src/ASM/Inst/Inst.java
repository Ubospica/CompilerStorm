package ASM.Inst;

import ASM.Operand.Operand;
import ASM.Operand.Reg;

public abstract class Inst {
	public Reg rd = null, rs1 = null;
	public Operand rs2 = null;
	public Operand imm = null;
	public abstract String toString();
}
