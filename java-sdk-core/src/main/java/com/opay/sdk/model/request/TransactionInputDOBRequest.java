package com.opay.sdk.model.request;

import lombok.Data;

@Data
public class TransactionInputDOBRequest {

    private String reference;
    private String orderNo;
    private String dob;

}
