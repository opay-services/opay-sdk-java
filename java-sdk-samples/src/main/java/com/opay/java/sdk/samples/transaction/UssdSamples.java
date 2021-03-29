package com.opay.java.sdk.samples.transaction;

import com.alibaba.fastjson.JSONObject;
import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.enums.TransactionStatus;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.TransactionUssdStatus;
import com.opay.sdk.model.request.TransactionStatusRequest;
import com.opay.sdk.model.request.TransactionUssdRequest;
import com.opay.sdk.model.response.TransactionUssdResponse;
import com.opay.sdk.model.response.TransactionUssdStatusResponse;

/**
 * Sample of transfer to Bank Account
 */
public class UssdSamples {

    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        UssdSamples samples = new UssdSamples();

        TransactionUssdResponse response = samples.init();
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
            TransactionUssdStatusResponse statusResponse = samples.queryStatus(reference);
            if (statusResponse == null) {
                System.out.println("Failed to query transaction status");
                return;
            }
            System.out.println("Query status response body:" + statusResponse);

            if (!statusResponse.success()) {
                System.out.println("Failed to query transaction status:" + statusResponse.getMessage());
                return;
            }

            TransactionUssdStatus data = statusResponse.getData();
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
            if (TransactionStatus.CLOSED.getValue().equals(data.getStatus())) {
                System.out.println("Transaction closed");
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
    public TransactionUssdResponse init() {
        TransactionUssdRequest request = new TransactionUssdRequest();
        request.setReference(System.currentTimeMillis() + "");
        request.setAmount("100");
        request.setProductDesc("xxxxxxxxxxx");
        request.setCurrency("NGN");
        request.setUserPhone("+2341231231230");
        request.setUserRequestIp("123.123.123.123");
        request.setCallbackUrl("http://www.baidu.com");
        request.setExpireAt(20);
        request.setBankCode("033");
        System.out.println(JSONObject.toJSONString(request));
        TransactionUssdResponse response = null;
        try {
            response = client.createUssdTransaction(request);
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
    public TransactionUssdStatusResponse queryStatus(String reference) {
        TransactionStatusRequest request = new TransactionStatusRequest();
        request.setReference(reference);
        TransactionUssdStatusResponse response = null;
        try {
            response = client.queryUssdTransactionStatus(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }
}
