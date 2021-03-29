package com.opay.sdk.model.request;

import com.opay.sdk.enums.CashierPayMethod;
import com.opay.sdk.enums.CashierPayType;
import lombok.Data;

import java.util.List;


@Data
public class CashierInitializeRequest {
    private String mchShortName;
    private String reference;
    private String userPhone;
    private String amount;
    private String currency = "NGN";
    private String userRequestIp;
    private String callbackUrl;
    private String returnUrl;
    private String productName;
    private String productDesc;
    private List<CashierPayMethod> payMethods;
    private List<CashierPayType> payTypes;
    private String expireAt;
    private String serviceId;
    private String merchantUserId;
    private String bankCode;
}
