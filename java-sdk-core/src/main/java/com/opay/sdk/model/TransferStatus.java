package com.opay.sdk.model;

import lombok.Data;

@Data
public class TransferStatus {

    private String reference;
    private String orderNo;
    private String amount;
    private String currency;
    private String status;
    private String failureReason;
    private String fee;

}
