package com.opay.sdk.model.response;

import com.opay.sdk.common.Response;
import com.opay.sdk.model.CashierInitialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class CashierInitializeResponse extends Response {

    private CashierInitialize data;
}
