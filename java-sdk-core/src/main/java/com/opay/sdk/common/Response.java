package com.opay.sdk.common;

import lombok.Data;

@Data
public class Response {

    private String code;

    private String message;

    public boolean success() {
        return "00000".equals(this.code);
    }
}
