package ASM.Inst;


import ASM.ASMBlock;
import ASM.ASMRoot;
import ASM.Operand.Reg;

/**
 *  beqz rs1, block
 *  beqz rs1, offset
 */
public class Beqz extends Inst {
	public ASMBlock dest;

	public Beqz(Reg rs1, ASMBlock dest) {
		this.rs1 = rs1;
		this.dest = dest;
	}

	@Override
	public String toString() {
		return String.format("beqz\t%s, %s", rs1, dest);
	}
}
