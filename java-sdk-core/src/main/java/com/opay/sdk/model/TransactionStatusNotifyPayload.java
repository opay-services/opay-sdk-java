package com.opay.sdk.model;

import lombok.Data;

@Data
public class TransactionStatusNotifyPayload {
    private String country;
    private String instrumentId;
    private String fee;
    private String channel;
    private String displayedFailure;
    private String reference;
    private String updatedAt;
    private String currency;
    private Boolean refunded;
    private String timestamp;
    private String amount;
    private String instrumentType;
    private String transactionId;
    private String token;
    private String bussinessType;
    private String payChannel;
    private String status;
    private String signature;

}
