package ASM.Operand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

abstract public class Reg extends Operand {
	public String id;
	public PhyReg color = null;

	public Reg(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return color == null ? id : color.id;
	}

	public boolean equals(Reg another) {
		var c1 = color == null ? this : color;
		var c2 = another.color == null ? another : another.color;
		return c1 == c2;
	}


	// Physical Registers

	public static ArrayList<PhyReg> regList = new ArrayList<>();
	public static ArrayList<PhyReg> callerSaveReg = new ArrayList<>();
	public static ArrayList<PhyReg> calleeSaveReg = new ArrayList<>();
	public static ArrayList<PhyReg> colorReg = new ArrayList<>();
	public static PhyReg zero, ra, sp, s0, a0;

	private static final List<String> regIdList = Arrays.asList(
			"zero", "ra", "sp", "gp", "tp", "t0", "t1", "t2", "s0", "s1", "a0", "a1", "a2", "a3", "a4", "a5",
			"a6", "a7", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "t3", "t4", "t5", "t6");

	// sp is treated independently
	private static final List<String> calleeSaveRegList = Arrays.asList(
			"s0", "s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11");
	private static final List<String> callerSaveRegList = Arrays.asList(
			"ra", "t0", "t1", "t2", "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "t3", "t4", "t5", "t6");
	// todo: swap color order
	private static final List<String> colorRegList = Arrays.asList(
			"t0", "t1", "t2", "t3", "t4", "t5", "t6", "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7",
			"s0", "s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "ra");
	private static final HashMap<String, PhyReg> regIdToReg = new HashMap<>();

	static {
		regIdList.forEach(x -> {
			var reg = new PhyReg(x);
			regList.add(reg);
			regIdToReg.put(x, reg);
		});
		calleeSaveRegList.forEach(x -> calleeSaveReg.add(getRegById(x)));
		callerSaveRegList.forEach(x -> callerSaveReg.add(getRegById(x)));
		colorRegList.forEach(x -> colorReg.add(getRegById(x)));
		// maybe bad practice...
		zero = getRegById("zero");
		ra = getRegById("ra");
		sp = getRegById("sp");
		a0 = getRegById("a0");
		s0 = getRegById("s0");
	}

	public static PhyReg getRegById(String name) {
		return regIdToReg.get(name);
	}

}
