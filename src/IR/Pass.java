package IR;

import IR.Value.Constant.IntConstant;
import IR.Value.Constant.NullConstant;
import IR.Value.Constant.StrConstant;
import IR.Value.Constant.ZeroInitConstant;
import IR.Value.Global.BasicBlock;
import IR.Value.Global.Function;
import IR.Value.Global.Module;
import IR.Value.Global.Variable;
import IR.Value.Inst.Inst;

public interface Pass {
	default void visit(Inst it) { }
	default void visit(Variable it) { }
	default void visit(Module it) { }
	default void visit(Function it) { }
	default void visit(BasicBlock it) { }
	default void visit(IntConstant it) { }
	default void visit(NullConstant it) { }
	default void visit(StrConstant it) { }
	default void visit(ZeroInitConstant it) { }
}
