package com.opay.sdk.model;

import com.opay.sdk.enums.ReceiverAccountTypeEnum;
import lombok.Data;

@Data
public class WalletReceiver {
    private String name;
    private ReceiverAccountTypeEnum type;
    private String phoneNumber;
    private String merchantId;
}
