package com.opay.sdk.model.request;

import com.opay.sdk.enums.PayTypeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class TransactionRequest implements Serializable {

    private static final long serialVersionUID = -1539468133090425341L;
    private String reference;
    private String amount;
    private String currency;
    private String country;
    private PayTypeEnum payType;
    private String cardNumber;
    private String cardDateMonth;
    private String cardDateYear;
    private String cardCVC;
    private String bankAccountNumber;
    private String bankCode;
    private String dobDay;
    private String dobMonth;
    private String dobYear;
    private String firstName;
    private String lastName;
    private String email;
    private String customerPhone;
    private String customerEmail;
    private String reason;
    private String token;
    private String bvn;
    private String callbackUrl;
    private String expireAt;
    private String return3dsUrl;
    private String clientSource = "Api";
    private String billingZip;
    private String billingCity;
    private String billingAddress;
    private String billingState;
    private String billingCountry;
}
