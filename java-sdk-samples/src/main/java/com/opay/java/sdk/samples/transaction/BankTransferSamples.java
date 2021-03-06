package com.opay.java.sdk.samples.transaction;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.enums.TransactionStatusEnum;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.BankTransferStatus;
import com.opay.sdk.model.request.TransactionBankTransferRequest;
import com.opay.sdk.model.request.TransactionBankTransferStatusRequest;
import com.opay.sdk.model.response.TransactionBankTransferResponse;
import com.opay.sdk.model.response.TransactionBankTransferStatusResponse;
import org.apache.http.util.Asserts;

/**
 * This sample is a bank transfer transaction
 * Step 1: Initialize the transaction
 * Step 2: After the customer transfer is completed, use reference to query the final status of the transaction
 */
public class BankTransferSamples {

    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        BankTransferSamples samples = new BankTransferSamples();

        TransactionBankTransferResponse response = samples.init();
        Asserts.notNull(response, "response");
        System.out.println(response);
        if (!response.success()) {
            System.out.println("Failed to initialize transaction");
            return;
        }
        String reference = response.getData().getReference();
        while (true) {
            TransactionBankTransferStatusResponse statusResponse = samples.queryStatus(reference);
            Asserts.notNull(statusResponse, "Query status response");
            System.out.println(statusResponse);
            if (!statusResponse.success()) {
                System.out.println("Failed to query transaction status");
                return;
            }
            BankTransferStatus data = statusResponse.getData();
            if (TransactionStatusEnum.PENDING.getValue().equals(data.getStatus()) || TransactionStatusEnum.INITIAL.getValue().equals(data.getStatus())) {
                System.out.println("Transaction processing");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            } else if (TransactionStatusEnum.SUCCESS.getValue().equals(data.getStatus())) {
                System.out.println("Transaction successful");
                break;
            } else if (TransactionStatusEnum.FAILED.getValue().equals(data.getStatus())) {
                System.out.println("Transaction failed");
                break;
            } else {
                System.out.println("Unknown status");
                break;
            }
        }

    }

    /**
     * init a transaction
     *
     * @return
     */
    public TransactionBankTransferResponse init() {
        TransactionBankTransferRequest request = new TransactionBankTransferRequest();
        request.setReference(System.currentTimeMillis() + "");
        request.setAmount("100");
        request.setProductDesc("xxxxxxxxxxx");
        request.setCurrency("NGN");
        request.setUserPhone("+2341231231230");
        request.setUserRequestIp("123.123.123.123");
        request.setCallbackUrl("http://www.baidu.com");
        request.setExpireAt("20");
        System.out.println(request);
        TransactionBankTransferResponse response = null;
        try {
            response = client.createBankTransferTransaction(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Query transaction status
     *
     * @return
     */
    public TransactionBankTransferStatusResponse queryStatus(String reference) {
        TransactionBankTransferStatusRequest request = new TransactionBankTransferStatusRequest();
        request.setReference(reference);
        System.out.println(request);
        TransactionBankTransferStatusResponse response = null;
        try {
            response = client.querybankTransferTransactionStatus(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }
}
