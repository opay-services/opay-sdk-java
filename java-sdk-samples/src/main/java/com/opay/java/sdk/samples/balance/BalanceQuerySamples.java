package com.opay.java.sdk.samples.balance;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.response.BalanceResponse;

/**
 * Sample of query OPay account balance.
 */
public class BalanceQuerySamples {


    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        BalanceResponse response;
        try {
            response = client.balances();
            if(response.success()){
                System.out.println(response);
            }else{
                System.out.println(String.format("code:%s, message:%s", response.getCode(), response.getMessage()));
            }
        } catch (OPayException e) {
            e.printStackTrace();
        }

    }
}
