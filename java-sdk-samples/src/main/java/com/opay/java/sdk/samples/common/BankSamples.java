package com.opay.java.sdk.samples.common;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.request.BankRequest;
import com.opay.sdk.model.response.BankResponse;
import org.apache.http.util.Asserts;

/**
 * This sample queries the banks supported by the transaction
 */
public class BankSamples {


    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        BankSamples samples = new BankSamples();
        BankResponse response = samples.banks();
        Asserts.notNull(response, "response");
        System.out.println(response);
        if (response.success()) {
            System.out.println("Query Successful");
        } else {
            System.out.println("Query Failed");
        }

    }

    public BankResponse banks() {
        BankRequest request = new BankRequest();
        request.setCountryCode("NG");
        System.out.println(request);
        BankResponse response = null;
        try {
            response = client.banks(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }
}
