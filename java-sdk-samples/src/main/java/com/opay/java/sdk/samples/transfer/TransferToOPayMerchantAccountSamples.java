package com.opay.java.sdk.samples.transfer;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.enums.ReceiverAccountTypeEnum;
import com.opay.sdk.enums.Status;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.TransferStatus;
import com.opay.sdk.model.WalletReceiver;
import com.opay.sdk.model.request.TransferStatusRequest;
import com.opay.sdk.model.request.TransferToWalletRequest;
import com.opay.sdk.model.response.TransferResponse;
import com.opay.sdk.model.response.TransferStatusResponse;

/**
 * Sample of transfer to OPay Merchant Account
 */
public class TransferToOPayMerchantAccountSamples {

    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {

        //The first step is to initiate a transfer transaction
        TransferResponse response = step1();
        if (response == null) {
            System.out.println("Failed to initialize transfer transaction");
            return;
        }
        System.out.println("Initialize transfer transaction response body:" + response);
        if (!response.success()) {
            System.out.println("Failed to initialize transfer transaction:" + response.getMessage());
            return;
        }
        System.out.println("Successfully initialized the transfer transaction");
        while (true) {
            TransferStatusResponse statusResponse = step2(response.getData().getReference());
            if (statusResponse == null) {
                System.out.println("Failed to query transaction status");
                return;
            }
            System.out.println("Query status response body:" + statusResponse);

            if (!statusResponse.success()) {
                System.out.println("Failed to query transaction status:" + statusResponse.getMessage());
                return;
            }
            TransferStatus data = statusResponse.getData();
            if (Status.SUCCESSFUL.getValue().equals(data.getStatus())) {
                System.out.println("Successful transaction");
                break;
            } else if (Status.PENDING.getValue().equals(data.getStatus()) || Status.INITIAL.getValue().equals(data.getStatus())) {
                System.out.println("Transaction processing");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (Status.CLOSED.getValue().equals(data.getStatus())) {
                System.out.println("Transaction closed");
                break;
            } else if (Status.FAILED.getValue().equals(data.getStatus())) {
                System.out.println("Transaction failed");
                break;
            } else {
                break;
            }
        }

    }

    /**
     * The first step is to initiate a transfer transaction
     *
     * @return
     */
    public static TransferResponse step1() {
        TransferToWalletRequest request = new TransferToWalletRequest();
        request.setReference(System.currentTimeMillis() + "");
        request.setAmount("100");
        request.setCountry("NG");
        request.setCurrency("NGN");
        request.setReason("xxxxxxxxx");
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

    /**
     * The second step is to query the transfer order status
     *
     * @param reference Merchant transaction number
     * @return
     */
    public static TransferStatusResponse step2(String reference) {
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
