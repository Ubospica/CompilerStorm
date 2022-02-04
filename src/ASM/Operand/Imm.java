package ASM.Operand;

public class Imm extends Operand {
	public int value;
	public static final int base = 1 << 12;
	public boolean determined;

	public Imm(int value) {
		this.value = value;
		determined = true;
	}

	public Imm() {
		this.value = 0;
		determined = false;
	}

	public boolean isLow() {
		return value < base && value >= -base;
	}

	public static boolean isLow(int value) {
		return value < base && value >= -base;
	}

	public Imm high() {
		return new Imm(value >> 12);
	}

	public Imm low() {
		return new Imm(value & (base - 1));
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
