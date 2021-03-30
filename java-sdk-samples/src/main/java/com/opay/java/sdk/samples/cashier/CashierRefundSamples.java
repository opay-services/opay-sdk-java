package com.opay.java.sdk.samples.cashier;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.enums.ReceiverAccountTypeEnum;
import com.opay.sdk.enums.RefundTypeEnum;
import com.opay.sdk.enums.TransactionStatusEnum;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.CashierRefund;
import com.opay.sdk.model.Receiver;
import com.opay.sdk.model.request.CashierRefundRequest;
import com.opay.sdk.model.request.CashierRefundStatusRequest;
import com.opay.sdk.model.response.CashierRefundResponse;
import com.opay.sdk.model.response.CashierRefundStatusResponse;
import org.apache.http.util.Asserts;

/**
 * This sample is a refund to the original payment account, bank account, OPay user account or OPay merchant account
 */
public class CashierRefundSamples {

    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {

        CashierRefundSamples samples = new CashierRefundSamples();

        //CashierRefundResponse response = samples.refundToBankAccount();

        //CashierRefundResponse response = samples.refundToOPayMerchantAccount();

        CashierRefundResponse response = samples.refundToOPayUserAccount();

        //CashierRefundResponse response = samples.refundToOriginalAccount();

        Asserts.notNull(response, "response");
        System.out.println(response);
        if (response.success()) {
            CashierRefund data = response.getData();
            while (true) {
                CashierRefundStatusResponse statusResponse = samples.queryRefundStatus(data.getReference());
                Asserts.notNull(statusResponse, "Query status response");
                System.out.println(statusResponse);
                String refundStatus = statusResponse.getData().getOrderStatus();
                if (TransactionStatusEnum.PENDING.getValue().equals(refundStatus) || TransactionStatusEnum.INITIAL.getValue().equals(refundStatus)) {
                    System.out.println("Transaction processing");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                } else if (TransactionStatusEnum.SUCCESS.getValue().equals(refundStatus)) {
                    System.out.println("Refund successfully");
                    break;
                } else {
                    System.out.println("Refund failed");
                    break;
                }

            }
        } else {
            System.out.println("Failed to initiate refund:" + response.getMessage());
        }
        return;
    }

    /**
     * Refund to the original payment account
     */
    public CashierRefundResponse refundToOriginalAccount() {
        CashierRefundRequest request = new CashierRefundRequest();
        request.setReference(System.currentTimeMillis() + "");
        request.setAmount("10");
        request.setCurrency("NGN");
        request.setCountry("NG");
        request.setOriginalReference("1617019195765");
        request.setReason("xxxxxxxxx");
        request.setRefundType(RefundTypeEnum.refundoriginal);
        System.out.println(request);
        CashierRefundResponse response = null;
        try {
            response = client.cashierRefund(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Refund to OPay bank account
     */
    public CashierRefundResponse refundToBankAccount() {
        CashierRefundRequest request = new CashierRefundRequest();
        request.setReference(System.currentTimeMillis() + "");
        request.setAmount("100");
        request.setCurrency("NGN");
        request.setCountry("NG");
        request.setOriginalReference("1616587871117");
        request.setBankAccountNumber("2070043686");
        request.setBankCode("033");
        request.setReason("xxxxxxxxxx");
        request.setRefundType(RefundTypeEnum.refund2bankaccount);
        System.out.println(request);
        CashierRefundResponse response = null;
        try {
            response = client.cashierRefund(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Refund to OPay user account
     */
    public CashierRefundResponse refundToOPayUserAccount() {
        CashierRefundRequest request = new CashierRefundRequest();
        request.setReference(System.currentTimeMillis() + "");
        request.setAmount("10");
        request.setCurrency("NGN");
        request.setCountry("NG");
        request.setOriginalReference("1617019195765");
        request.setReason("xxxx");
        request.setRefundType(RefundTypeEnum.refund2opayaccount);
        Receiver receiver = new Receiver();
        receiver.setType(ReceiverAccountTypeEnum.USER);
        receiver.setPhoneNumber("+2348160564736");
        request.setReceiver(receiver);
        System.out.println(request);
        CashierRefundResponse response = null;
        try {
            response = client.cashierRefund(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Refund to OPay merchant account
     */
    public CashierRefundResponse refundToOPayMerchantAccount() {
        CashierRefundRequest request = new CashierRefundRequest();
        request.setReference(System.currentTimeMillis() + "");
        request.setAmount("100");
        request.setCurrency("NGN");
        request.setCountry("NG");
        request.setOriginalReference("1616573829117");
        request.setReason("xxxxxxxxxxxxx");
        request.setRefundType(RefundTypeEnum.refund2opayaccount);
        Receiver receiver = new Receiver();
        receiver.setType(ReceiverAccountTypeEnum.MERCHANT);
        receiver.setMerchantId("256620112018025");
        request.setReceiver(receiver);
        System.out.println(request);
        CashierRefundResponse response = null;
        try {
            response = client.cashierRefund(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }

    public CashierRefundStatusResponse queryRefundStatus(String reference) {
        CashierRefundStatusRequest request = new CashierRefundStatusRequest();
        request.setReference(reference);
        System.out.println(request);
        CashierRefundStatusResponse response = null;
        try {
            response = client.cashierRefundStatus(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }
}
