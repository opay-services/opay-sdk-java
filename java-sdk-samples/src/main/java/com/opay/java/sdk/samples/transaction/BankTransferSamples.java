package com.opay.java.sdk.samples.transaction;

import com.alibaba.fastjson.JSONObject;
import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.enums.TransactionStatus;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.BankTransferStatus;
import com.opay.sdk.model.request.TransactionBankTransferRequest;
import com.opay.sdk.model.request.TransactionBankTransferStatusRequest;
import com.opay.sdk.model.response.TransactionBankTransferResponse;
import com.opay.sdk.model.response.TransactionBankTransferStatusResponse;

/**
 * Sample of transfer to Bank Account
 */
public class BankTransferSamples {

    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {


        TransactionBankTransferResponse response = init();
        if (response == null) {
            System.out.println("Failed to initialize transaction");
            return;
        }
        System.out.println("Initialize transaction response body:" + response);
        if (!response.success()) {
            System.out.println("Failed to initialize transaction:" + response.getMessage());
            return;
        }
        String reference = response.getData().getReference();
        System.out.println("Successfully initialized the transaction");
        while (true) {
            TransactionBankTransferStatusResponse statusResponse = queryStatus(reference);
            if (statusResponse == null) {
                System.out.println("Failed to query transaction status");
                return;
            }
            System.out.println("Query status response body:" + statusResponse);

            if (!statusResponse.success()) {
                System.out.println("Failed to query transaction status:" + statusResponse.getMessage());
                return;
            }

            BankTransferStatus data = statusResponse.getData();
            if (TransactionStatus.PENDING.getValue().equals(data.getStatus()) || TransactionStatus.INITIAL.getValue().equals(data.getStatus())) {
                System.out.println("Transaction processing");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (TransactionStatus.SUCCESS.getValue().equals(data.getStatus())) {
                System.out.println("Successful transaction");
                break;
            }

            if (TransactionStatus.FAILED.getValue().equals(data.getStatus())) {
                System.out.println("Transaction failed");
                break;
            }
        }

    }

    /**
     * init a transaction
     *
     * @return
     */
    public static TransactionBankTransferResponse init() {
        TransactionBankTransferRequest request = new TransactionBankTransferRequest();
        request.setReference(System.currentTimeMillis() + "");
        request.setAmount("100");
        request.setProductDesc("xxxxxxxxxxx");
        request.setCurrency("NGN");
        request.setUserPhone("+2341231231230");
        request.setUserRequestIp("123.123.123.123");
        request.setCallbackUrl("http://www.baidu.com");
        request.setExpireAt("20");
        System.out.println(JSONObject.toJSONString(request));
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
    public static TransactionBankTransferStatusResponse queryStatus(String reference) {
        TransactionBankTransferStatusRequest request = new TransactionBankTransferStatusRequest();
        request.setReference(reference);
        TransactionBankTransferStatusResponse response = null;
        try {
            response = client.querybankTransferTransactionStatus(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }
}
