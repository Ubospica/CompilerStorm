package ASM.Inst;

import ASM.Operand.Imm;
import ASM.Operand.Operand;
import ASM.Operand.Reg;
import ASM.Operand.Symbol;
import Backend.ASMPrinter;
import org.antlr.v4.codegen.model.SrcOp;


/**
 *  lw rd, offset(rs1)
 *  lw rd, symbol
 */
public class Lw extends Inst {
	public Reg rd = null, rs1 = null;
	public Operand offset = null;

	public Lw(Reg rd, Reg rs1, Imm offset) {
		this.rd = rd;
		this.rs1 = rs1;
		this.offset = offset;
	}

	public Lw(Reg rd, Symbol offset) {
		this.rd = rd;
		this.offset = offset;
	}

	@Override
	public String toString() {
		if (offset instanceof Symbol sVal){
			return String.format("lw\t%s, %s", rd, sVal);
		} else {
			return String.format("lw\t%s, %s(%s)", rd, offset, rs1);
		}
	}
}
