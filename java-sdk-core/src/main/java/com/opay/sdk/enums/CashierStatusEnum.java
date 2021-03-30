package com.opay.sdk.enums;

import lombok.Getter;

@Getter
public enum CashierStatusEnum {
    INITIAL("INITIAL"),
    PENDING("PENDING"),
    SUCCESS("SUCCESS"),
    FAIL("FAIL"),
    CLOSE("CLOSE");

    private String value;

    CashierStatusEnum(String value) {
        this.value = value;
    }
}
