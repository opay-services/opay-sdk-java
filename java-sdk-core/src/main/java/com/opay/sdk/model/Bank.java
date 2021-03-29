package com.opay.sdk.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author wangjialei
 */
@Data
@Builder
public class Bank {
    private String code;
    private String name;
    private String type;
    //private String icon;
}
