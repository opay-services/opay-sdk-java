package com.opay.sdk.model.response;

import com.opay.sdk.common.Response;
import com.opay.sdk.model.AirtimeTopup;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class AirtimeTopupStatusResponse extends Response {

    private List<AirtimeTopup> data;
}
