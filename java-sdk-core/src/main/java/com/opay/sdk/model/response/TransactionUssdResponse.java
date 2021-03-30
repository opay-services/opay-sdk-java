package com.opay.sdk.model.response;

import com.opay.sdk.common.Response;
import com.opay.sdk.model.TransactionUssd;
import lombok.Data;
import lombok.ToString;

@Data()
@ToString(callSuper = true)
public class TransactionUssdResponse extends Response {

    private TransactionUssd data;

}
