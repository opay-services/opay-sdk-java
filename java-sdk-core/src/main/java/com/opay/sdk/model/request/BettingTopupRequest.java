package com.opay.sdk.model.request;


import com.opay.sdk.model.Bulk;
import lombok.Data;

import java.util.List;

@Data
public class BettingTopupRequest {

    private String serviceType = "betting";

    private String callBackUrl;

    private List<Bulk> bulkData;
}
