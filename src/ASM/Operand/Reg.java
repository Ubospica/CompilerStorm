package ASM.Operand;

import ASM.ASMFunc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

abstract public class Reg extends Operand {
	public String id;

	public Reg(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id;
	}


	// Physical Registers

	public static ArrayList<PhyReg> regList = new ArrayList<>();
	public static ArrayList<PhyReg> callerSaveReg = new ArrayList<>();
	public static ArrayList<PhyReg> calleeSaveReg = new ArrayList<>();

	private static final List<String> regIdList = Arrays.asList(
			"zero", "ra", "sp", "gp", "tp", "t0", "t1", "t2", "s0", "s1", "a0", "a1", "a2", "a3", "a4", "a5",
			"a6", "a7", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "t3", "t4", "t5", "t6");
	// preserved: callee-save
	private static final List<String> preservedRegList = Arrays.asList(
			"sp", "s0", "s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11");
	private static final HashMap<String, PhyReg> regIdToReg = new HashMap<>();


	static {
		regIdList.forEach(x -> {
			var reg = new PhyReg(x);
			regList.add(reg);
			regIdToReg.put(x, reg);
			if (preservedRegList.contains(x)) {
				calleeSaveReg.add(reg);
			} else {
				callerSaveReg.add(reg);
			}
		});
	}

	public static PhyReg getRegById(String name) {
		return regIdToReg.get(name);
	}

}
