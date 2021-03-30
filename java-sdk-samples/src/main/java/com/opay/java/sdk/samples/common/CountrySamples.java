package com.opay.java.sdk.samples.common;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.response.CountryResponse;
import org.apache.http.util.Asserts;

/**
 * This sample is to query the currently supported countries
 */
public class CountrySamples {


    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        CountrySamples samples = new CountrySamples();
        CountryResponse response = samples.countries();
        Asserts.notNull(response, "response");
        System.out.println(response);
        if (response.success()) {
            System.out.println("Query Successful");
        } else {
            System.out.println("Query Failed");
        }
    }

    public CountryResponse countries() {
        CountryResponse response = null;
        try {
            response = client.countries();

        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }
}
