package com.opay.java.sdk.samples.cashier;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.request.CashierCloseRequest;
import com.opay.sdk.model.response.CashierCloseResponse;

public class CashierCloseSamples {


    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        CashierCloseRequest request = new CashierCloseRequest();
        request.setReference("1616573150477");
        CashierCloseResponse response = null;
        try {
            response = client.cashierClose(request);
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
