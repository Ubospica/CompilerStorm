package IR.Value.Constant;

import IR.Pass;
import IR.Type.ArrayType;
import IR.Type.IntType;
import IR.Type.PointerType;
import IR.Type.Type;

//@.str = private unnamed_addr constant [9 x i8] c"\\\0A\0D\09\22\00", align 1
// char *s = "\\\n\r\t\"";

public class StrConstant extends Constant {
	public String value;

	public StrConstant(String value) {
		super(null);
//		super(new PointerType(new ArrayType(IntType.INT8, value.length() + 1)));
		String realVal = value.replace("\\\\", "\\")
				.replace("\\n", "\n")
				.replace("\\r", "\r")
				.replace("\\t", "\t")
				.replace("\\\"", "\"");
		type = new PointerType(new ArrayType(IntType.INT8, realVal.length() + 1));

		this.value = realVal;
	}

	public String getIrString() {
		return value.replace("\\", "\\\\")
				.replace("\n", "\\0A")
				.replace("\r", "\\0D")
				.replace("\t", "\\09")
				.replace("\"", "\\22") + "\\00";
	}

	public String getAsmString() {
		return value.replace("\\", "\\\\")
				.replace("\n", "\\n")
				.replace("\r", "\\r")
				.replace("\t", "\\t")
				.replace("\"", "\\\"");
	}

	public void accept(Pass pass) {
		pass.visit(this);
	}


}
