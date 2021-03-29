package com.opay.sdk.model;

import lombok.Data;

@Data
public class CashierInitialize {
    private String orderNo;
    private String reference;
    private String cashierUrl;
    private String status;
    private String amount;
    private String currency;
}
