package com.opay.sdk.model.request;

import com.opay.sdk.model.BulkStatus;
import lombok.Data;

import java.util.List;

@Data
public class BettingStatusRequest {

    List<BulkStatus> bulkStatusRequest;
    private String serviceType = "betting";
}
