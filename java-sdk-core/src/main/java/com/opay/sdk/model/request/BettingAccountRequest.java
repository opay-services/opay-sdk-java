package com.opay.sdk.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class BettingAccountRequest implements Serializable {

    private String serviceType = "betting";
    private String provider;
    private String customerId;
}
