package com.opay.sdk.model.response;

import com.opay.sdk.common.Response;
import com.opay.sdk.model.TransactionUssd;
import lombok.Data;

@Data
public class TransactionUssdResponse extends Response {

    private TransactionUssd data;

}
