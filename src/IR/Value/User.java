package IR.Value;

import IR.Type.Type;

import java.util.LinkedList;

abstract public class User extends Value {
	public LinkedList<Use> operandList = new LinkedList<>();

	public User(Type type) {
		super(type);
	}

	public void addUse(Value val) {
		Use.getUseLink(this, val);
	}

	public Value getUse(int idx) {
		return operandList.get(idx).val;
	}

	public int getOperandCnt() {
		return operandList.size();
	}
}
