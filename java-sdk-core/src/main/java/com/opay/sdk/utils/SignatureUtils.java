package com.opay.sdk.utils;

import com.opay.sdk.exception.OPaySignException;
import com.opay.sdk.model.TopupStatusNotifyPayload;
import com.opay.sdk.model.TransactionStatusNotifyPayload;

/**
 * Verification signature tools
 *
 * @author wanjun.an
 * @since 1.0
 */
public class SignatureUtils {

    public static boolean verifyTransactionSignature(TransactionStatusNotifyPayload payload, String secretKey) throws OPaySignException {
        String format = "{Amount:\"%s\",Currency:\"%s\",Reference:\"%s\",Refunded:%s,Status:\"%s\",Timestamp:\"%s\",Token:\"%s\",TransactionID:\"%s\"}";
        String signString = String.format(format,
                payload.getAmount(),
                payload.getCurrency(),
                payload.getReference(),
                true == payload.getRefunded() ? "t" : "f",
                payload.getStatus(),
                payload.getTimestamp(),
                payload.getToken() == null && payload.getToken().length() == 0 ? "" : payload.getToken(),
                payload.getTransactionId()
        );
        String sign = HmacUtils.buildHmacSha3_512(signString, secretKey);
        if (payload.getSignature().equalsIgnoreCase(sign)) {
            return true;
        }
        throw new OPaySignException();
    }

    public static boolean verifyTopupSignature(TopupStatusNotifyPayload payload, String secretKey) throws OPaySignException {
        String format = "{orderNo:\"%s\",merchantOrderNo:\"%s\",merchantId:\"%s\",orderAmount:\"%s\",serviceType:\"%s\",orderStatus:\"%s\"}";
        String signString = String.format(format,
                payload.getOrderNo(),
                payload.getMerchantOrderNo(),
                payload.getMerchantId(),
                payload.getOrderAmount(),
                payload.getServiceType(),
                payload.getOrderStatus()
        );
        String sign = HmacUtils.buildHmacSha3_512(signString, secretKey);
        if (payload.getSignature().equalsIgnoreCase(sign)) {
            return true;
        }
        throw new OPaySignException();
    }

}
