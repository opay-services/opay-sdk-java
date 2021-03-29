package com.opay.sdk.model;

import lombok.Data;

@Data
public class BankReceiver {
    private String name;
    private String bankCode;
    private String bankAccountNumber;
    private String nameCheck;
}
