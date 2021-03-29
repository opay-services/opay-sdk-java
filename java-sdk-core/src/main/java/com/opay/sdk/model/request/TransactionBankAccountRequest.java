package com.opay.sdk.model.request;

import com.opay.sdk.enums.PayTypeEnum;
import lombok.Data;

@Data
public class TransactionBankAccountRequest {

    private String reference;
    private String amount;
    private String currency;
    private String country;
    private PayTypeEnum payType = PayTypeEnum.bankcard;
    private String bankAccountNumber;
    private String bankCode;
    private String return3dsUrl;
    private String reason;
    private String customerPhone;
    private String bvn;
    private String dobDay;
    private String dobMonth;
    private String dobYear;
    private String callbackUrl;
    private String expireAt;
}
