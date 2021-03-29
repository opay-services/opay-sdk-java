package com.opay.sdk.model;

import lombok.Data;

@Data
public class TransferReceiver {
    private String name;
    private String type;
    private String phoneNumber;
    private String merchantId;
    private String bankCode;
    private String bankAccountNumber;
    private String nameCheck;
}
