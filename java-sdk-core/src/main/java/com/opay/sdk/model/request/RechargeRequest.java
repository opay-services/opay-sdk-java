package com.opay.sdk.model.request;

import lombok.Data;

@Data
public class RechargeRequest {
    private String reference;
    private String amount;
    private String currency;
    private String userRequestIp;
    private String callbackUrl;
    private String returnUrl;
    private String expireAt;
    private String countryCode;
    private String chargerType;
    private String chargerId;
}
