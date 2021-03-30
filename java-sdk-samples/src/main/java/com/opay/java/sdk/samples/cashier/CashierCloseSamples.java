package com.opay.java.sdk.samples.cashier;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.request.CashierCloseRequest;
import com.opay.sdk.model.response.CashierCloseResponse;
import org.apache.http.util.Asserts;


/**
 * This is a sample of closing a transaction
 */
public class CashierCloseSamples {


    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        CashierCloseSamples samples = new CashierCloseSamples();
        CashierCloseResponse response = samples.close();
        Asserts.notNull(response, "response");
        System.out.println(response);
        if (response.success()) {
            System.out.println("Close Successful");
        } else {
            System.out.println("Close Failed:" + response.getMessage());
        }
    }


    public CashierCloseResponse close() {
        CashierCloseRequest request = new CashierCloseRequest();
        request.setReference("1616573150477");
        System.out.println(request);
        CashierCloseResponse response = null;
        try {
            response = client.cashierClose(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }
}
