package IR.Value;


public class Use {
	public User user;
	public Value val;

	public Use(User user, Value val) {
		this.user = user;
		this.val = val;
	}

	public boolean equals(Use another) {
		return user == another.user && val == another.val;
	}

	public static Use getUseLink(User user, Value val) {
		Use ret = new Use(user, val);
		user.operandList.add(ret);
		val.useList.add(ret);
		return ret;
	}

	public static void removeUse(User user, Value val) {
		var use = new Use(user, val);
		user.operandList.remove(use);
		val.useList.remove(use);
	}
}
