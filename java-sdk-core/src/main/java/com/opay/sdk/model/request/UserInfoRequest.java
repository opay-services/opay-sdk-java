package com.opay.sdk.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoRequest implements Serializable {

    private String phoneNumber;

}
