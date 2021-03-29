package com.opay.java.sdk.samples.betting;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.BettingAccount;
import com.opay.sdk.model.request.BettingAccountRequest;
import com.opay.sdk.model.response.BettingAccountResponse;

/**
 * This sample is to verify the customerId of a specific provider
 */
public class BettingAccountQuerySamples {

    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        BettingAccountQuerySamples samples = new BettingAccountQuerySamples();
        BettingAccount data = samples.validate();
        System.out.println(data);
    }

    public BettingAccount validate() {
        BettingAccountRequest request = new BettingAccountRequest();
        request.setProvider("CLOUDBET");
        request.setCustomerId("1234344324324");
        try {
            BettingAccountResponse response = client.validateBettingCustomerId(request);
            if (!response.success()) {
                throw new OPayException(response.getMessage());
            }
            return response.getData();
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return null;
    }
}
