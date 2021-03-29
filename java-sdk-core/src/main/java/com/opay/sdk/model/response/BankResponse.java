package com.opay.sdk.model.response;

import com.opay.sdk.common.Response;
import com.opay.sdk.model.Bank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class BankResponse extends Response {

    private List<Bank> data;
}
