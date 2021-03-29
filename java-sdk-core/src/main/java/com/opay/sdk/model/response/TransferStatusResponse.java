package com.opay.sdk.model.response;

import com.opay.sdk.common.Response;
import com.opay.sdk.model.TransferStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class TransferStatusResponse extends Response {

    private TransferStatus data;
}
