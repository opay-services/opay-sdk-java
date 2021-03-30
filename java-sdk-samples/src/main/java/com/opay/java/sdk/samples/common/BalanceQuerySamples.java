package com.opay.java.sdk.samples.common;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.response.BalanceResponse;
import org.apache.http.util.Asserts;

/**
 * This sample is to get OPay account balance.
 */
public class BalanceQuerySamples {


    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        BalanceQuerySamples samples = new BalanceQuerySamples();
        BalanceResponse response = samples.balance();
        Asserts.notNull(response, "response");
        System.out.println(response);
        if (response.success()) {
            System.out.println("Query Successful");
        } else {
            System.out.println("Query Failed");
        }
    }

    public BalanceResponse balance() {
        BalanceResponse response = null;
        try {
            response = client.balances();
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }
}
