package com.opay.java.sdk.samples.betting;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.enums.TopupStatusEnum;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.BettingTopup;
import com.opay.sdk.model.Bulk;
import com.opay.sdk.model.BulkStatus;
import com.opay.sdk.model.request.BettingStatusRequest;
import com.opay.sdk.model.request.BettingTopupRequest;
import com.opay.sdk.model.response.BettingTopupResponse;
import com.opay.sdk.model.response.BettingTopupStatusResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This sample is a betting account recharge.
 * step 1: initiate a recharge transaction.
 * step 2: check the status to confirm the final result.
 */
public class BettingTopupSamples {

    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        BettingTopupSamples samples = new BettingTopupSamples();
        BettingTopupResponse response = samples.init();
        if (response == null) {
            System.out.println("Failed to initialize transaction");
            return;
        }
        System.out.println(response);
        if (!response.success()) {
            System.out.println("Failed to initialize transaction:" + response.getMessage());
            return;
        }
        System.out.println("Successful to initialize transaction");
        List<String> references = response.getData().stream()
                .map(BettingTopup::getReference)
                .collect(Collectors.toList());

        //Query transaction status cyclically
        for (String reference : references) {
            while (true) {
                BettingTopupStatusResponse statusResponse = samples.queryStatus(reference);
                System.out.println(statusResponse);
                if (statusResponse == null) {
                    System.out.println("Failed to query status");
                    break;
                }
                if (!statusResponse.success()) {
                    System.out.println("Failed to query status:" + response.getMessage());
                    break;
                }
                BettingTopup data = statusResponse.getData().get(0);
                if (TopupStatusEnum.SUCCESS.getValue().equals(data.getStatus())) {
                    System.out.println("Successful transaction");
                    break;
                }
                if (TopupStatusEnum.PENDING.getValue().equals(data.getStatus()) || TopupStatusEnum.INITIAL.getValue().equals(data.getStatus())) {
                    System.out.println("Transaction processing");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                if (TopupStatusEnum.FAIL.getValue().equals(data.getStatus())) {
                    System.out.println("Transaction Failed:" + data.getErrorMsg());
                    break;
                }
            }
        }


    }

    /**
     * Initialize the airtime recharge transaction
     *
     * @return
     */
    public BettingTopupResponse init() {
        BettingTopupRequest request = new BettingTopupRequest();
        List<Bulk> datas = new ArrayList<Bulk>();
        Bulk data = new Bulk();
        data.setReference(System.currentTimeMillis() + "");
        data.setAmount("100");
        data.setCountry("NG");
        data.setCurrency("NGN");
        data.setCustomerId("100");
        data.setProvider("NAIRABET");
        datas.add(data);
        request.setBulkData(datas);
        System.out.println(request);
        BettingTopupResponse response = null;
        try {
            response = client.createBettingTopupTranaction(request);
            System.out.println(response);
            if (!response.success()) {
                throw new OPayException(response.getMessage());
            }
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Query airtime recharge transaction status.
     * @param references
     * @return
     */
    public BettingTopupStatusResponse queryStatus(String references) {
        BettingStatusRequest request = new BettingStatusRequest();
        List<BulkStatus> datas = new ArrayList();
        BulkStatus data = new BulkStatus();
        data.setReference(references);
        datas.add(data);
        request.setBulkStatusRequest(datas);
        System.out.println(request);

        BettingTopupStatusResponse response = null;
        try {
            response = client.queryBettingTopupTranactionStatus(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }
}
