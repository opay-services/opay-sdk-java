package com.opay.sdk.model.request;

import com.opay.sdk.model.BankReceiver;
import lombok.Data;

@Data
public class TransferToBankRequest {

    private String reference;
    private String amount;
    private String currency;
    private String country;
    private BankReceiver receiver;
    private String reason;
}
