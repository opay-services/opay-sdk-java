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
 * This sample is to transfer money to a OPay user account
 * Step 1: Initialize the transaction
 * Step 2: Use reference to query the final status of the transaction
 */
public class TransferToOPayUserAccountSamples {

    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        TransferToOPayUserAccountSamples samples = new TransferToOPayUserAccountSamples();
        TransferResponse response = init();
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

    public static TransferResponse init() {
        TransferToWalletRequest request = new TransferToWalletRequest();
        request.setReference(System.currentTimeMillis() + "");
        request.setAmount("100");
        request.setCountry("NG");
        request.setCurrency("NGN");
        request.setReason("xxxxxxxxx");
        WalletReceiver receiver = new WalletReceiver();
        receiver.setPhoneNumber("+2348160564736");
        receiver.setType(ReceiverAccountTypeEnum.USER);
        request.setReceiver(receiver);
        TransferResponse response = null;
        try {
            response = client.transferToWallet(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static TransferStatusResponse queryStatus(String reference) {
        TransferStatusRequest request = new TransferStatusRequest();
        request.setReference(reference);
        TransferStatusResponse response = null;
        try {
            response = client.queryTransferToWalletStatus(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }
}
