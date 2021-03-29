package com.opay.sdk.model;

import lombok.Data;

@Data
public class Transaction {
    private String orderNo;
    private String reference;
    private String amount;
    private String currency;
    private String status;
    private String token;
    private String authURL;
    private String failureReason;

}
