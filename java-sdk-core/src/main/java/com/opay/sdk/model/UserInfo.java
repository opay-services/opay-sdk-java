package com.opay.sdk.model;

import lombok.Data;

@Data
public class UserInfo {

    private String userId;

    private String phoneNumber;

    private String firstName;

    private String lastName;

    private String email;

    private String address;
}
