package com.opay.sdk.model.request;

import lombok.Data;

@Data
public class TransactionInputOTPRequest {

    private String reference;
    private String otp;

}
