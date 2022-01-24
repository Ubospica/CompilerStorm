package ASM;


import java.util.ArrayList;

public class ASMFunc {
	public String id;
	public ArrayList<ASMBlock> blocks = new ArrayList<>();

	public ASMFunc(String id) {
		this.id = id;
	}
}
