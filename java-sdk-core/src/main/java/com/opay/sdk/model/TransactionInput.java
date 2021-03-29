package com.opay.sdk.model;

import lombok.Data;

@Data
public class TransactionInput {
    String status;
    String orderNo;
    String reference;
    String token;
    String authURL;
    String failureReason;
}
