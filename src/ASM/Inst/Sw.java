package ASM.Inst;

import ASM.Operand.Imm;
import ASM.Operand.Operand;
import ASM.Operand.Reg;
import ASM.Operand.Symbol;

/**
 *  sw rs2, offset(rs1)
 *  sw rs2, symbol, rs1
 */
public class Sw extends Inst {

	public Sw(Reg rs2, Reg rs1, Imm imm) {
		this.rs2 = rs2;
		this.rs1 = rs1;
		this.imm = imm;
	}

	public Sw(Reg rs2, Reg rt, Symbol imm) {
		this.rs2 = rs2;
		this.rd = rt;
		this.imm = imm;
	}

	@Override
	public String toString() {
		if (imm instanceof Symbol sVal) {
			return String.format("sw\t%s, %s, %s", rs2, sVal, rd);
		} else {
			return String.format("sw\t%s, %s(%s)", rs2, imm, rs1);
		}
	}
}
