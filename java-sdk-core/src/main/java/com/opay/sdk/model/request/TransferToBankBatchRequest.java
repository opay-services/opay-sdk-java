package com.opay.sdk.model.request;

import lombok.Data;

import java.util.List;

@Data
public class TransferToBankBatchRequest {

    private List<TransferToBankRequest> list;
}
