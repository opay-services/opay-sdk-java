package com.opay.sdk.enums;

import lombok.Getter;

@Getter
public enum Status {
    INITIAL("INITIAL"),
    PENDING("PENDING"),
    SUCCESSFUL("SUCCESSFUL"),
    FAILED("FAILED"),
    CLOSED("CLOSED");

    private String value;

    Status(String value) {
        this.value = value;
    }
}
