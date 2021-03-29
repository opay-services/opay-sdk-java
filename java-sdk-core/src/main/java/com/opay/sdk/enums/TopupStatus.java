package com.opay.sdk.enums;

import lombok.Getter;

@Getter
public enum TopupStatus {
    INITIAL("INITIAL"),
    PENDING("PENDING"),
    SUCCESS("SUCCESS"),
    FAIL("FAIL");

    private String value;

    TopupStatus(String value) {
        this.value = value;
    }
}
