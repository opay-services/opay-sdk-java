package com.opay.java.sdk.samples.common;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.request.MerchantInfoRequest;
import com.opay.sdk.model.response.MerchantInfoResponse;

/**
 * This sample is for querying merchant information
 */
public class MerchantInfoSamples {


    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        MerchantInfoSamples samples = new MerchantInfoSamples();
        MerchantInfoResponse response = samples.merchantInfo();
        System.out.println(response);
        if (response.success()) {
            System.out.println("Query Successful");
        } else {
            System.out.println("Query Failed");
        }
    }

    public MerchantInfoResponse merchantInfo() {
        MerchantInfoRequest request = new MerchantInfoRequest();
        request.setEmail("kekekeke@opay.com");
        System.out.println(request);
        MerchantInfoResponse response = null;
        try {
            response = client.merchantInfo(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;

    }
}
