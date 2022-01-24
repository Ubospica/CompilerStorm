package Util.Error;

import Util.Position;

public class InternalError extends MxError {
	public InternalError(String msg, Position pos) {
		super("InternalError: " + msg, pos);
	}
}
