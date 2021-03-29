package com.opay.sdk.model;

import lombok.Data;


@Data
public class Bulk {

    private String reference;
    private String country;
    private String currency;
    private String amount;
    private String customerId;
    private String provider;

}
