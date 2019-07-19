package com.yueyue.exceptions;


public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 3868757075383035401L;

    private final String msg;

    public BadRequestException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BadRequestException(String msg, Throwable t) {
        super(msg, t);
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }
}
