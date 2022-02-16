package ASM.Inst;

import ASM.ASMFunc;
import ASM.Operand.Reg;

import java.util.ArrayList;

import static java.lang.Math.min;

/**
 *  call func
 *  call offset
 */
public class Call extends Inst {
	public ASMFunc dest;

	public Call(ASMFunc dest) {
		// todo: call inst use list & def list
		this.dest = dest;
	}

	@Override
	public String toString() {
		return String.format("call\t%s", dest);
	}

	@Override
	public ArrayList<Reg> getUseList() {
		var res = new ArrayList<Reg>();
		for (var i = 0; i < min(8, dest.argSize - 1); ++i) {
			res.add(Reg.getRegById("a" + i));
		}
		return res;
	}

	@Override
	public ArrayList<Reg> getDefList() {
		return new ArrayList<Reg>(Reg.callerSaveReg);
	}

}
