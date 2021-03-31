package com.opay.java.sdk.samples.topup;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.exception.OPaySignException;
import com.opay.sdk.model.TopupStatusNotifyPayload;
import com.opay.sdk.utils.SignatureUtils;


/**
 * This sample is to verify the airtime/betting callback signature
 */
public class CallbackVerifySignSamples {

    public static void main(String[] args) {
        TopupStatusNotifyPayload payload = new TopupStatusNotifyPayload();
        payload.setMerchantId("256620112018025");
        payload.setMerchantOrderNo("testlijian_1617156457495873001");
        payload.setOrderAmount(50000L);
        payload.setOrderNo("210331130540182885");
        payload.setOrderStatus("SUCCESS");
        payload.setServiceType("Betting");
        payload.setSignature("580e776a435c68950a405ea9bb6b9ec3228e4db9c81757ceaf76637a39ec5683e31ced93c642bf1c820343f289151b4b254a11c48aa1c877f0d90972063827bf");
        try {
            boolean result = SignatureUtils.verifyTopupSignature(payload, Config.PRIVATE_KEY);
            if (result) {
                System.out.println("Verified successfully");
            }
        } catch (OPaySignException e) {
            e.printStackTrace();
        }
    }

}
