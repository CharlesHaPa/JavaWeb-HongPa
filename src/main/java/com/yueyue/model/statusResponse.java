package com.yueyue.model;

public class statusResponse {

    private final boolean success;
    private final String message;

    public statusResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public statusResponse(boolean success) {
        this.success = success;
        this.message = null;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

}
