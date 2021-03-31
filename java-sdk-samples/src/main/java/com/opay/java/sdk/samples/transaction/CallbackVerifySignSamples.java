package com.opay.java.sdk.samples.transaction;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.exception.OPaySignException;
import com.opay.sdk.model.TransactionStatusNotifyPayload;
import com.opay.sdk.utils.SignatureUtils;


/**
 * This sample is to verify the transaction callback signature
 */
public class CallbackVerifySignSamples {

    public static void main(String[] args) {
        TransactionStatusNotifyPayload payload = new TransactionStatusNotifyPayload();
        payload.setAmount("1.00");
        payload.setCurrency("NGN");
        payload.setReference("testlijian_1617094127536759000");
        payload.setRefunded(false);
        payload.setStatus("successful");
        payload.setToken("210330140540064091");
        payload.setTransactionId("210330140540064091");
        payload.setTimestamp("2021-03-30T08:48:58Z");
        payload.setSignature("2d67f294357caeda84636ba68e2b74723924b9ec1c626bb732e56ca9633b0dffd2963e82cae34940fba1147306fcef8223d69bd0419fe02025ecae5933f4d96a0");
        try {
            boolean result = SignatureUtils.verifyTransactionSignature(payload, Config.PRIVATE_KEY);
            if (result) {
                System.out.println("Verified successfully");
            }
        } catch (OPaySignException e) {
            e.printStackTrace();
        }
    }

}
