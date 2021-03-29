package com.opay.sdk.model.response;

import com.opay.sdk.common.Response;
import com.opay.sdk.model.AccountBalance;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class BalanceResponse extends Response {

    private AccountBalance data;
}
