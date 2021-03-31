package com.opay.java.sdk.samples.topup;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.request.BettingAccountRequest;
import com.opay.sdk.model.response.BettingAccountResponse;
import org.apache.http.util.Asserts;

/**
 * This sample is for querying betting account information
 */
public class BettingAccountQuerySamples {

    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        BettingAccountQuerySamples samples = new BettingAccountQuerySamples();
        BettingAccountResponse response = samples.query();
        Asserts.notNull(response, "response");
        System.out.println(response);
        if (response.success()) {
            System.out.println("Query Successful");
        } else {
            System.out.println("Query Failed");
        }
    }

    public BettingAccountResponse query() {
        BettingAccountRequest request = new BettingAccountRequest();
        request.setProvider("CLOUDBET");
        request.setCustomerId("123456789");
        System.out.println(request);
        BettingAccountResponse response = null;
        try {
            response = client.validateBettingCustomerId(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }
}
