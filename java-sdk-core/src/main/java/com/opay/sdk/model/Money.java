package com.opay.sdk.model;

import lombok.Data;

@Data
public class Money {

    private String currency;

    private Long amount;
}
