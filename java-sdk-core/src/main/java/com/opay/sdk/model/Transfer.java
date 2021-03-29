package com.opay.sdk.model;

import lombok.Data;

@Data
public class Transfer {

    private String reference;
    private String orderNo;
    private String amount;
    private String currency;
    private String fee;
    private String status;
    private String failureReason;

}
