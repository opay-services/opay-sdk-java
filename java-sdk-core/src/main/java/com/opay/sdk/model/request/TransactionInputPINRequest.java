package com.opay.sdk.model.request;

import lombok.Data;

@Data
public class TransactionInputPINRequest {

    private String reference;
    private String pin;

}
