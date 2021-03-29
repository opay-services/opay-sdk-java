package com.opay.sdk.model;

import lombok.Data;

@Data
public class TransactionUssdStatus {
    private String orderNo;
    private String reference;
    private String status;
    private String amount;
    private String currency;
}
