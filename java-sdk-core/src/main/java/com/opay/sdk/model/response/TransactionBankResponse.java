package com.opay.sdk.model.response;

import com.opay.sdk.model.Bank;
import lombok.Data;

import java.util.List;

@Data
public class TransactionBankResponse {

    private List<Bank> banks;

}
