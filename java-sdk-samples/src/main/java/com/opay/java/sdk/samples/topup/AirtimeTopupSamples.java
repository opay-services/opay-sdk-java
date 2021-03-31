package com.opay.java.sdk.samples.topup;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.enums.TopupStatusEnum;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.AirtimeTopup;
import com.opay.sdk.model.Bulk;
import com.opay.sdk.model.BulkStatus;
import com.opay.sdk.model.request.AirtimeTopupRequest;
import com.opay.sdk.model.request.AirtimeTopupStatusRequest;
import com.opay.sdk.model.response.AirtimeTopupResponse;
import com.opay.sdk.model.response.AirtimeTopupStatusResponse;
import org.apache.http.util.Asserts;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This sample is a airtime account recharge.
 * step 1: initiate a recharge transaction.
 * step 2: check the status to confirm the final result.
 */
public class AirtimeTopupSamples {

    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        AirtimeTopupSamples samples = new AirtimeTopupSamples();
        AirtimeTopupResponse response = samples.init();
        Asserts.notNull(response, "response");
        System.out.println("Initialize transaction response:" + response);
        if (!response.success()) {
            System.out.println("Failed to initialize transaction:" + response.getMessage());
            return;
        }
        System.out.println("Successful to initialize transaction");
        List<String> references = response.getData().stream()
                .map(AirtimeTopup::getReference)
                .collect(Collectors.toList());

        //Query transaction status cyclically
        for (String reference : references) {
            while (true) {
                AirtimeTopupStatusResponse statusResponse = samples.queryStatus(reference);
                Asserts.notNull(response, "Query status response");
                System.out.println(statusResponse);
                if (!statusResponse.success()) {
                    System.out.println(response.getMessage());
                    break;
                }
                AirtimeTopup data = statusResponse.getData().get(0);
                if (TopupStatusEnum.SUCCESS.getValue().equals(data.getStatus())) {
                    System.out.println("Successful transaction");
                    break;
                } else if (TopupStatusEnum.PENDING.getValue().equals(data.getStatus()) || TopupStatusEnum.INITIAL.getValue().equals(data.getStatus())) {
                    System.out.println("Transaction processing");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                } else if (TopupStatusEnum.FAIL.getValue().equals(data.getStatus())) {
                    System.out.println("Transaction Failed:" + data.getErrorMsg());
                    break;
                } else {
                    System.out.println("Unknown status");
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
    public AirtimeTopupResponse init() {
        AirtimeTopupRequest request = new AirtimeTopupRequest();
        List<Bulk> datas = new ArrayList<Bulk>();
        Bulk data = new Bulk();
        data.setReference(System.currentTimeMillis() + "");
        data.setAmount("100");
        data.setCountry("NG");
        data.setCurrency("NGN");
        data.setCustomerId("100");
        data.setProvider("AIR");
        datas.add(data);
        request.setBulkData(datas);
        System.out.println(request);
        AirtimeTopupResponse response = null;
        try {
            response = client.createAirtimeTopup(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Query transaction status
     *
     * @param reference
     * @return
     */
    public AirtimeTopupStatusResponse queryStatus(String reference) {
        AirtimeTopupStatusRequest request = new AirtimeTopupStatusRequest();
        List<BulkStatus> datas = new ArrayList();
        BulkStatus data = new BulkStatus();
        data.setReference(reference);
        datas.add(data);
        request.setBulkStatusRequest(datas);
        System.out.println(request);

        AirtimeTopupStatusResponse response = null;
        try {
            response = client.queryAirtimeTopupStatus(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }
}
