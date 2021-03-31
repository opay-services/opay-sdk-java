package com.opay.java.sdk.samples.topup;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.response.BettingProviderResponse;
import org.apache.http.util.Asserts;


/**
 * This sample is to get a list of betting providers.
 */
public class BettingProviderQuerySamples {

    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        BettingProviderQuerySamples samples = new BettingProviderQuerySamples();
        BettingProviderResponse response = samples.query();
        Asserts.notNull(response, "response");
        System.out.println(response);
        if (response.success()) {
            System.out.println("Query Successful");
        } else {
            System.out.println("Query Failed");
        }
    }


    public BettingProviderResponse query() {
        BettingProviderResponse response = null;
        try {
            response = client.queryBettingProviders();
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }
}
