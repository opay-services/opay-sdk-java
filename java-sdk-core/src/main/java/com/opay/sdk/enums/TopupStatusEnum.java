package com.opay.sdk.enums;

import lombok.Getter;

@Getter
public enum TopupStatusEnum {
    INITIAL("INITIAL"),
    PENDING("PENDING"),
    SUCCESS("SUCCESS"),
    FAIL("FAIL");

    private String value;

    TopupStatusEnum(String value) {
        this.value = value;
    }
}
