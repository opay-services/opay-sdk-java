package com.opay.sdk.model.response;

import com.opay.sdk.common.Response;
import com.opay.sdk.model.BankTransferStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class TransactionBankTransferStatusResponse extends Response {

    private BankTransferStatus data;
}
