package com.opay.sdk.model.response;

import com.opay.sdk.common.Response;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class TransferBatchResponse extends Response {

    private Boolean data;
}
