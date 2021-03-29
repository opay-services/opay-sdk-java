package com.opay.sdk.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class BulkStatus implements Serializable {

    /**
     * 商户自己订单号
     */
    private String reference;
}
