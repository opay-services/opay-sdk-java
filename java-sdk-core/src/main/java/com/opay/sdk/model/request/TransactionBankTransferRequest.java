package com.opay.sdk.model.request;

import lombok.Data;

@Data
public class TransactionBankTransferRequest {
    private String reference;
    private String productDesc;
    private String userPhone;
    private String userRequestIp;
    private String amount;
    private String currency;
    private String callbackUrl;
    private String expireAt;
}
