package ASM.Inst;

import ASM.Operand.Imm;
import ASM.Operand.Operand;
import ASM.Operand.Reg;

import java.util.ArrayList;
import java.util.List;

public abstract class Inst {
	public Reg rd = null, rs1 = null, rs2 = null; // virtual reg or phy reg
	public Operand imm = null; // imm or symbol
	public abstract String toString();

	public ArrayList<Reg> getUseList() {
		return rs1 == null ? new ArrayList<>() :
				rs2 == null ? new ArrayList<>(List.of(rs1)) : new ArrayList<>(List.of(rs1, rs2));
	}

	public ArrayList<Reg> getDefList() {
		return rd == null ? new ArrayList<>() : new ArrayList<>(List.of(rd));
	}

	public boolean isUndetermined() {
		return imm instanceof Imm newImm && !newImm.determined;
	}

	public int getImmVal() {
		return ((Imm) imm).value;
	}

	public void setImmVal(int val) {
		((Imm) imm).value = val;
		((Imm) imm).determined = true;
	}
}
