package com.opay.java.sdk.samples.cashier;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.enums.ReceiverAccountTypeEnum;
import com.opay.sdk.enums.RefundTypeEnum;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.Receiver;
import com.opay.sdk.model.request.CashierRefundRequest;
import com.opay.sdk.model.response.CashierRefundResponse;

public class CashierRefundSamples {

    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {

        CashierRefundSamples samples = new CashierRefundSamples();

        //samples.refundToBankAccount();

        //samples.refundToOPayMerchantAccount();

        //samples.refundToOPayUserAccount();

        samples.refundToOriginalAccount();

    }

    public void refundToOriginalAccount() {
        CashierRefundRequest request = new CashierRefundRequest();
        request.setReference(System.currentTimeMillis() + "");
        request.setAmount("10");
        request.setCurrency("NGN");
        request.setCountry("NG");
        request.setOriginalReference("1616590227375");
        request.setReason("xxxxxxxxx");
        request.setRefundType(RefundTypeEnum.refundoriginal);
        CashierRefundResponse response = null;
        try {
            response = client.cashierRefund(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        System.out.println(response);
    }

    public void refundToBankAccount() {
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
        CashierRefundResponse response = null;
        try {
            response = client.cashierRefund(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        System.out.println(response);
    }

    public void refundToOPayUserAccount() {
        CashierRefundRequest request = new CashierRefundRequest();
        request.setReference(System.currentTimeMillis() + "");
        request.setAmount("10");
        request.setCurrency("NGN");
        request.setCountry("NG");
        request.setOriginalReference("1616577151798");
        request.setReason("xxxx");
        request.setRefundType(RefundTypeEnum.refund2opayaccount);
        Receiver receiver = new Receiver();
        receiver.setType(ReceiverAccountTypeEnum.USER);
        receiver.setPhoneNumber("+2348160564736");
        request.setReceiver(receiver);
        CashierRefundResponse response = null;
        try {
            response = client.cashierRefund(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        System.out.println(response);
    }

    public void refundToOPayMerchantAccount() {
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
        CashierRefundResponse response = null;
        try {
            response = client.cashierRefund(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        System.out.println(response);
    }
}
