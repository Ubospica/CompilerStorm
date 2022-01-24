package ASM.Operand;

public class VirtualReg extends Reg {
	public VirtualReg(String id) {
		super(id);
	}

	public VirtualReg() {
		this("tmp");
	}
}
