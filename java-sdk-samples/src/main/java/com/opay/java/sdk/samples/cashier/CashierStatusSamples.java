package com.opay.java.sdk.samples.cashier;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.CashierStatusEnum;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.request.CashierStatusRequest;
import com.opay.sdk.model.response.CashierStatusResponse;
import org.apache.http.util.Asserts;

/**
 * This is a sample for querying transaction status
 */
public class CashierStatusSamples {


    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        CashierStatusSamples samples = new CashierStatusSamples();
        CashierStatusResponse response = samples.status();
        System.out.println(response);
        Asserts.check(response != null, "Failed to query transaction status:response is null");
        if (response.success()) {
            String status = response.getData().getStatus();
            if (CashierStatusEnum.INITIAL.getValue().equals(status) || CashierStatusEnum.PENDING.getValue().equals(status)) {
                System.out.println("Transaction processing");
            } else if (CashierStatusEnum.SUCCESS.getValue().equals(status)) {
                System.out.println("Transaction Successful");
            } else if (CashierStatusEnum.CLOSE.getValue().equals(status)) {
                System.out.println("Transaction closed");
            } else {
                System.out.println("Transaction failed");
            }
        } else {
            System.out.println("Query Failed:" + response.getMessage());
        }
    }

    public CashierStatusResponse status() {
        CashierStatusRequest request = new CashierStatusRequest();
        request.setReference("1616590227375");
        System.out.println(request);
        CashierStatusResponse response = null;
        try {
            response = client.cashierStatus(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;

    }
}
