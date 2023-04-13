package com.tacs.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private final Integer statusCode;

    public ApiException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public ApiException(String message, Throwable cause, Integer statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }
}
