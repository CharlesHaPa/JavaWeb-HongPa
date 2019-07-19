package com.yueyue.exceptions;

public class ConflictException extends RuntimeException {

    private static final long serialVersionUID = 264720308843146266L;
    private final String msg;

    public ConflictException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ConflictException(String msg, Throwable t) {
        super(msg, t);
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }
}
