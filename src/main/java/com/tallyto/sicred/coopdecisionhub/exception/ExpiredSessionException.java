package com.tallyto.sicred.coopdecisionhub.exception;

public class ExpiredSessionException extends ValidationException {
    public ExpiredSessionException(String message) {
        super(message);
    }
    public ExpiredSessionException(String message, Throwable  cause) {
        super(message, cause);
    }
}
