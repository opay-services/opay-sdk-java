package com.opay.sdk.enums;

import lombok.Getter;

@Getter
public enum TransactionStatus {
    INITIAL("INITIAL"),
    PENDING("PENDING"),
    INPUT_PIN("INPUT-PIN"),
    INPUT_OTP("INPUT-OTP"),
    INPUT_PHONE("INPUT-PHONE"),
    INPUT_DOB("INPUT-DOB"),
    _3DSECURE("3DSECURE"),
    SUCCESS("SUCCESS"),
    FAILED("FAIL"),
    CLOSED("CLOSED");

    private String value;

    TransactionStatus(String value) {
        this.value = value;
    }
}
