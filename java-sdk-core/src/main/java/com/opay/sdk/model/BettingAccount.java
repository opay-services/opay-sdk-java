package com.opay.sdk.model;

import lombok.Data;

@Data
public class BettingAccount {
    private String provider;
    private String customerId;
    private String firstName;
    private String lastName;
    private String userName;
}
