package com.opay.java.sdk.samples.betting;

import com.alibaba.fastjson.JSON;
import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.BettingProvider;
import com.opay.sdk.model.response.BettingProviderResponse;

import java.util.List;


/**
 * Query sample of betting provider list.
 */
public class BettingProviderQuerySamples {

    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        BettingProviderQuerySamples samples = new BettingProviderQuerySamples();
        List<BettingProvider> providers = samples.query();
        System.out.println(JSON.toJSONString(providers));
    }


    public List<BettingProvider> query() {
        List<BettingProvider> providers = null;
        try {
            BettingProviderResponse response = client.queryBettingProviders();
            if (!response.success()) {
                throw new OPayException(response.getMessage());
            }
            providers = response.getData();
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return providers;
    }
}
