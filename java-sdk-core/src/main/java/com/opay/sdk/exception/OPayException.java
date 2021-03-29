package com.opay.sdk.exception;

/**
 * OPayException handles all exceptions related to REST services
 */
public class OPayException extends Exception {

    public OPayException(String message) {
        super(message);
    }

    public OPayException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public OPayException(Throwable throwable) {
        super(throwable);
    }
}
