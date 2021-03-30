package com.opay.sdk.enums;

import lombok.Getter;

@Getter
public enum TransferStatusEnum {
    INITIAL("INITIAL"),
    PENDING("PENDING"),
    SUCCESSFUL("SUCCESSFUL"),
    FAILED("FAILED"),
    CLOSED("CLOSED");

    private String value;

    TransferStatusEnum(String value) {
        this.value = value;
    }
}
