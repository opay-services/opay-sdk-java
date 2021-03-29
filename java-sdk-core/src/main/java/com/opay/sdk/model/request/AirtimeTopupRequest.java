package com.opay.sdk.model.request;


import com.opay.sdk.model.Bulk;
import lombok.Data;

import java.util.List;

@Data
public class AirtimeTopupRequest {

    private String serviceType = "airtime";

    private String callBackUrl;

    private List<Bulk> bulkData;
}
