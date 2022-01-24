package IR.Value;


public class Use {
	public User user;
	public Value val;

	private Use(User user, Value val) {
		this.user = user;
		this.val = val;
	}

	public static Use getUseLink(User user, Value val) {
		Use ret = new Use(user, val);
		user.operandList.add(ret);
		val.useList.add(ret);
		return ret;
	}
}
