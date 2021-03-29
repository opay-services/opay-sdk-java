package com.opay.sdk.model.request;

import lombok.Data;

@Data
public class TransactionInputPhoneRequest {

    private String reference;
    private String orderNo;
    private String phone;

}
