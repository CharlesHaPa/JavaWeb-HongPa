package com.yueyue.exceptions;

public class ForbiddenException extends RuntimeException {

    private static final long serialVersionUID = 6943182409707880364L;
    private final String msg;

    public ForbiddenException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ForbiddenException(String msg, Throwable t) {
        super(msg, t);
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }
}
