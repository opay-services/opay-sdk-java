package com.opay.sdk.model.request;

import lombok.Data;

@Data
public class TransactionStatusRequest {

    private String orderNo;

    private String reference;
}
