package Util.Error;

import Util.Position;

public class SementicError extends MxError {
    public SementicError(String msg, Position pos) {
        super("SementicError: " + msg, pos);
    }
}
