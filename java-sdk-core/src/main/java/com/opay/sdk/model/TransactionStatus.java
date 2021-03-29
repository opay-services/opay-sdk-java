package com.opay.sdk.model;

import lombok.Data;

@Data
public class TransactionStatus {
    private String amount;
    private String authURL;
    private String currency;
    private String failureReason;
    private String reference;
    private String status;
    private String token;
    private String orderNo;

}
