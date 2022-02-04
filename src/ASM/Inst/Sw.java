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
	public Reg rs2, rs1;
	public Operand offset;

	public Sw(Reg rs2, Reg rs1, Imm offset) {
		this.rs2 = rs2;
		this.rs1 = rs1;
		this.offset = offset;
	}

	public Sw(Reg rs2, Reg rs1, Symbol offset) {
		this.rs2 = rs2;
		this.rs1 = rs1;
		this.offset = offset;
	}

	@Override
	public String toString() {
		if (offset instanceof Symbol sVal) {
			return String.format("sw\t%s, %s, %s", rs2, sVal, rs1);
		} else {
			return String.format("sw\t%s, %s(%s)", rs2, offset, rs1);
		}
	}
}
