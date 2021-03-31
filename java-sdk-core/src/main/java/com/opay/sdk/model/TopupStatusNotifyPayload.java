package com.opay.sdk.model;

import lombok.Data;

@Data
public class TopupStatusNotifyPayload {
    private String orderNo;
    private String merchantOrderNo;
    private String merchantId;
    private Long orderAmount;
    private String serviceType;
    private String orderStatus;
    private String signature;
}
