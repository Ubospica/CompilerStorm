package Util.Error;

import Util.Position;

public abstract class MxError extends RuntimeException {
	private final Position pos;
	private final String message;

	public MxError (String msg, Position pos) {
		this.pos = pos;
		this.message = msg;
	}

	public String toString() {
		return message + ": " + pos.toString();
	}
}
