package com.opay.sdk.model;

import lombok.Data;

@Data
public class CashierClose {
    private String orderNo;
    private String reference;
    private String status;
}
