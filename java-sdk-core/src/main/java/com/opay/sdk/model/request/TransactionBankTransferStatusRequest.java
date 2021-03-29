package com.opay.sdk.model.request;

import lombok.Data;

@Data
public class TransactionBankTransferStatusRequest {

    private String orderNo;

    private String reference;
}
