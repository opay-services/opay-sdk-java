package com.opay.sdk.model;

import com.opay.sdk.enums.ReceiverAccountTypeEnum;
import lombok.Data;

@Data
public class Receiver {

    private ReceiverAccountTypeEnum type;

    private String merchantId;

    private String phoneNumber;
}