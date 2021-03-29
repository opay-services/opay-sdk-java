package com.opay.sdk.model.request;

import com.opay.sdk.enums.PayTypeEnum;
import lombok.Data;

@Data()
public class TransactionBankCardRequest {

    private String reference;
    private String amount;
    private String currency;
    private String country;
    private PayTypeEnum payType = PayTypeEnum.bankcard;
    private String firstName;
    private String lastName;
    private String customerEmail;
    private String cardNumber;
    private String cardDateMonth;
    private String cardDateYear;
    private String cardCVC;
    private String return3dsUrl;
    private String bankCode;
    private String reason;
    private String callbackUrl;
    private String expireAt;
    private String billingZip;
    private String billingCity;
    private String billingAddress;
    private String billingState;
    private String billingCountry;

}
