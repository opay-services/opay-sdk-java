package com.opay.sdk.model.request;

import lombok.Data;

import java.util.List;

@Data
public class TransferToWalletBatchRequest {

    private List<TransferToWalletRequest> list;

}
