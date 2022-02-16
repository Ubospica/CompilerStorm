package ASM.Operand;

public class Symbol extends Operand {
	String id;
	public Symbol(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id;
	}

	public String hi() {
		return String.format("%%hi(%s)", id);
		// return String.format("%%pcrel_hi(%s)", id);
	}

	public String lo() {
		return String.format("%%lo(%s)", id);
		// return String.format("%%pcrel_lo(%s)", id);
	}
}
