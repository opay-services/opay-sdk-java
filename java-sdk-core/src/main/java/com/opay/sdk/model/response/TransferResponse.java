package com.opay.sdk.model.response;

import com.opay.sdk.common.Response;
import com.opay.sdk.model.Transfer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class TransferResponse extends Response {

    private Transfer data;
}
