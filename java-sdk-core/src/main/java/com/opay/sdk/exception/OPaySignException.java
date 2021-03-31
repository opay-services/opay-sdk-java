package com.opay.sdk.exception;

public class OPaySignException extends Exception {

    public OPaySignException() {
        super("Signature verification failed");
    }
}
