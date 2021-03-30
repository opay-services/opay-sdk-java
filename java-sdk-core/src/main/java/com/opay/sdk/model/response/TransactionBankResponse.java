package com.opay.sdk.model.response;

import com.opay.sdk.model.Bank;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
public class TransactionBankResponse {

    private List<Bank> banks;

}
