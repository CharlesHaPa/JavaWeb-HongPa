package com.yueyue.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExceptionJsonInfo {

    private final String message;

    public ExceptionJsonInfo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
