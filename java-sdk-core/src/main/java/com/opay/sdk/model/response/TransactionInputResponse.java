package com.opay.sdk.model.response;

import com.opay.sdk.common.Response;
import com.opay.sdk.model.TransactionInput;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class TransactionInputResponse extends Response {

    private TransactionInput data;
}
