package com.opay.java.sdk.samples.cashier;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.request.CashierStatusRequest;
import com.opay.sdk.model.response.CashierStatusResponse;

public class CashierStatusSamples {


    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        CashierStatusRequest request = new CashierStatusRequest();
        request.setReference("1616590227375");
        CashierStatusResponse response = null;
        try {
            response = client.cashierStatus(request);
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
