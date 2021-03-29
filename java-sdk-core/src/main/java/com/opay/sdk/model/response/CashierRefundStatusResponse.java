package com.opay.sdk.model.response;

import com.opay.sdk.common.Response;
import com.opay.sdk.model.CashierRefundStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class CashierRefundStatusResponse extends Response {


    private CashierRefundStatus data;
}
