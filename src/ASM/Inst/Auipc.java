package ASM.Inst;

import ASM.Operand.Operand;
import ASM.Operand.Reg;
import ASM.Operand.Symbol;

// auipc rd, %hi(Symbol)
public class Auipc extends Inst {

	public Auipc(Reg rd, Operand imm) {
		this.rd = rd;
		this.imm = imm;
	}

	@Override
	public String toString() {
		if (imm instanceof Symbol sVal) {
			return String.format("auipc %s, %s", rd, sVal.hi());
		} else {
			return String.format("auipc %s, %s", rd, imm);
		}
	}
}