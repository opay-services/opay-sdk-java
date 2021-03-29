package com.opay.sdk.model.request;

import com.opay.sdk.enums.RefundTypeEnum;
import com.opay.sdk.model.Receiver;
import lombok.Data;

@Data
public class CashierRefundRequest {
    private String reference;
    private String originalReference;
    private String amount;
    private String currency;
    private String country;
    private String bankAccountNumber;
    private String bankCode;
    private Receiver receiver;
    private RefundTypeEnum refundType;
    private String reason;
}
