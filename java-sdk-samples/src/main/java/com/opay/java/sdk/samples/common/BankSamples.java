package com.opay.java.sdk.samples.common;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.request.BankRequest;
import com.opay.sdk.model.response.BankResponse;

public class BankSamples {


    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        BankRequest request = new BankRequest();
        request.setCountryCode("NG");
        BankResponse response = null;
        try {
            response = client.banks(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        if(response.success()){
            System.out.println(response);
        }else{
            System.out.println(String.format("code:%s, message:%s", response.getCode(), response.getMessage()));
        }
    }
}
