package com.opay.sdk.model.request;

import com.opay.sdk.enums.CashierPayMethodEnum;
import com.opay.sdk.enums.CashierPayTypeEnum;
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
    private List<CashierPayMethodEnum> payMethods;
    private List<CashierPayTypeEnum> payTypes;
    private String expireAt;
    private String serviceId;
    private String merchantUserId;
}
