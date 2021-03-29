package com.opay.sdk.model.request;

import com.opay.sdk.model.WalletReceiver;
import lombok.Data;

@Data
public class TransferToWalletRequest {

    private String reference;
    private String amount;
    private String currency;
    private String country;
    private WalletReceiver receiver;
    private String reason;
}
