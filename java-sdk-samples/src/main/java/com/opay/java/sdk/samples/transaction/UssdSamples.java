package com.opay.java.sdk.samples.transaction;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.enums.TransactionStatusEnum;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.TransactionUssdStatus;
import com.opay.sdk.model.request.TransactionStatusRequest;
import com.opay.sdk.model.request.TransactionUssdRequest;
import com.opay.sdk.model.response.TransactionUssdResponse;
import com.opay.sdk.model.response.TransactionUssdStatusResponse;
import org.apache.http.util.Asserts;

/**
 * This sample is a USSD transaction
 * Step 1: Initialize the transaction
 * Step 2: After the customer dials, call the reference to query the final status of the transaction
 */
public class UssdSamples {

    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        UssdSamples samples = new UssdSamples();

        TransactionUssdResponse response = samples.init();
        Asserts.notNull(response, "response");
        System.out.println(response);
        if (!response.success()) {
            System.out.println("Failed to initialize transaction");
            return;
        }
        String reference = response.getData().getReference();
        while (true) {
            TransactionUssdStatusResponse statusResponse = samples.queryStatus(reference);
            Asserts.notNull(statusResponse, "Query status response");
            System.out.println(statusResponse);
            if (!statusResponse.success()) {
                System.out.println("Failed to query transaction status");
                return;
            }
            TransactionUssdStatus data = statusResponse.getData();
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
    public TransactionUssdResponse init() {
        TransactionUssdRequest request = new TransactionUssdRequest();
        request.setReference(System.currentTimeMillis() + "");
        request.setAmount("100");
        request.setProductDesc("xxxxxxxxxxx");
        request.setCurrency("NGN");
        request.setUserPhone("+2341231231230");
        request.setUserRequestIp("123.123.123.123");
        request.setCallbackUrl("http://www.baidu.com");
        request.setExpireAt("20");
        request.setBankCode("033");
        System.out.println(request);
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
        System.out.println(request);
        TransactionUssdStatusResponse response = null;
        try {
            response = client.queryUssdTransactionStatus(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }
}
