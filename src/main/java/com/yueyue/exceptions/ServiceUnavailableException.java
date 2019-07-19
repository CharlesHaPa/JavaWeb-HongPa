package com.yueyue.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "Service Unavailable")
public class ServiceUnavailableException extends RuntimeException {

    private static final long serialVersionUID = -2776055426513273775L;

    public ServiceUnavailableException() {
        super("Service Unavailable");
    }

    public ServiceUnavailableException(Throwable t) {
        super("Service Unavailable", t);
    }
}
