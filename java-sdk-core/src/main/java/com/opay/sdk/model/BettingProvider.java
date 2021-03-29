package com.opay.sdk.model;

import lombok.Data;

@Data
public class BettingProvider {

    private String provider;
    private String providerLogoUrl;
    private Money minAmount;
    private Money maxAmount;
}
