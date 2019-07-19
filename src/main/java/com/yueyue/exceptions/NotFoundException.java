package com.yueyue.exceptions;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7881314766842956031L;
    private final String msg;

    public NotFoundException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public NotFoundException(String msg, Throwable t) {
        super(msg, t);
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }
}
