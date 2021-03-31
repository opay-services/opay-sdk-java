package com.opay.java.sdk.samples.transfer;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.enums.ReceiverAccountTypeEnum;
import com.opay.sdk.enums.TransferStatusEnum;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.TransferStatus;
import com.opay.sdk.model.WalletReceiver;
import com.opay.sdk.model.request.TransferStatusRequest;
import com.opay.sdk.model.request.TransferToWalletRequest;
import com.opay.sdk.model.response.TransferResponse;
import com.opay.sdk.model.response.TransferStatusResponse;
import org.apache.http.util.Asserts;

/**
 * This sample is to transfer money to a OPay merchant account
 * Step 1: Initialize the transaction
 * Step 2: Use reference to query the final status of the transaction
 */
public class TransferToOPayMerchantAccountSamples {

    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        TransferToOPayMerchantAccountSamples samples = new TransferToOPayMerchantAccountSamples();
        TransferResponse response = samples.init();
        Asserts.notNull(response, "response");
        System.out.println(response);
        if (!response.success()) {
            System.out.println("Failed to initialize transfer transaction");
            return;
        }
        while (true) {
            TransferStatusResponse statusResponse = queryStatus(response.getData().getReference());
            Asserts.notNull(response, "response");
            System.out.println(statusResponse);
            if (!statusResponse.success()) {
                System.out.println("Failed to query transaction status");
                return;
            }
            TransferStatus data = statusResponse.getData();
            if (TransferStatusEnum.SUCCESSFUL.getValue().equals(data.getStatus())) {
                System.out.println("Transaction Successful");
                break;
            } else if (TransferStatusEnum.PENDING.getValue().equals(data.getStatus()) || TransferStatusEnum.INITIAL.getValue().equals(data.getStatus())) {
                System.out.println("Transaction processing");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            } else if (TransferStatusEnum.CLOSED.getValue().equals(data.getStatus())) {
                System.out.println("Transaction closed");
                break;
            } else if (TransferStatusEnum.FAILED.getValue().equals(data.getStatus())) {
                System.out.println("Transaction failed");
                break;
            } else {
                System.out.println("Unknown status");
                break;
            }
        }

    }

    public static TransferStatusResponse queryStatus(String reference) {
        TransferStatusRequest request = new TransferStatusRequest();
        request.setReference(reference);
        System.out.println(request);
        TransferStatusResponse response = null;
        try {
            response = client.queryTransferToWalletStatus(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }

    public TransferResponse init() {
        TransferToWalletRequest request = new TransferToWalletRequest();
        request.setReference(System.currentTimeMillis() + "");
        request.setAmount("100");
        request.setCountry("NG");
        request.setCurrency("NGN");
        request.setReason("xxxxxxxxx");
        System.out.println(request);
        WalletReceiver receiver = new WalletReceiver();
        receiver.setMerchantId("256620112018025");
        receiver.setType(ReceiverAccountTypeEnum.MERCHANT);
        request.setReceiver(receiver);
        TransferResponse response = null;
        try {
            response = client.transferToWallet(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }
}
