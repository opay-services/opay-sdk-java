package com.opay.java.sdk.samples.transaction;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.enums.PayTypeEnum;
import com.opay.sdk.enums.TransactionStatusEnum;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.TransactionStatus;
import com.opay.sdk.model.request.*;
import com.opay.sdk.model.response.TransactionInputResponse;
import com.opay.sdk.model.response.TransactionResponse;
import com.opay.sdk.model.response.TransactionStatusResponse;
import org.apache.http.util.Asserts;

/**
 * This sample demonstrates how to create a bank account transaction
 * Step 1: Initialize the transaction
 * Step 2: Use reference to query transaction status to determine what to do next
 */
public class BankAccountTransactionSamples {

    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        TransactionResponse response = init();
        Asserts.notNull(response, "response");
        System.out.println(response);
        if (!response.success()) {
            System.out.println("Failed to initialize transaction:" + response.getMessage());
            return;
        }
        System.out.println("Successfully initialized the transaction");
        String reference = response.getData().getReference();
        while (true) {
            TransactionStatusResponse statusResponse = queryStatus(reference);
            Asserts.notNull(response, "Query status response");
            System.out.println("Query status response:" + statusResponse);

            Asserts.check(response.success(), "Query status response");

            TransactionStatus data = statusResponse.getData();
            if (TransactionStatusEnum.PENDING.getValue().equals(data.getStatus()) || TransactionStatusEnum.INITIAL.getValue().equals(data.getStatus())) {
                System.out.println("Transaction processing");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (TransactionStatusEnum.SUCCESS.getValue().equals(data.getStatus())) {
                System.out.println("Transaction Successful");
                break;
            }
            if (TransactionStatusEnum.FAILED.getValue().equals(data.getStatus())) {
                System.out.println("Transaction Failed");
                break;
            }
            if (TransactionStatusEnum.INPUT_PIN.getValue().equals(data.getStatus())) {
                TransactionInputResponse inputResponse = verifyPIN(reference, "1105");
                if (inputResponse.success()) {
                    Asserts.check(inputResponse.success(), "PIN verification failed:");
                }
                System.out.println("PIN verification successful");
                continue;
            }
            if (TransactionStatusEnum.INPUT_OTP.getValue().equals(data.getStatus())) {
                TransactionInputResponse inputResponse = verifyOTP(reference, "543210");
                if (inputResponse.success()) {
                    Asserts.check(inputResponse.success(), "OTP verification failed:");
                }
                System.out.println("OTP verification successful");
                continue;
            }
            if (TransactionStatusEnum.INPUT_PHONE.getValue().equals(data.getStatus())) {
                TransactionInputResponse inputResponse = verifyPhone(reference, "+2341234567890");
                if (inputResponse.success()) {
                    Asserts.check(inputResponse.success(), "Phone verification failed:");
                }
                System.out.println("Phone verification successful");
                continue;
            }
            if (TransactionStatusEnum.INPUT_DOB.getValue().equals(data.getStatus())) {
                TransactionInputResponse inputResponse = verifyDOB(reference, "11/11/11");
                if (inputResponse.success()) {
                    Asserts.check(inputResponse.success(), "DOB verification failed:");
                }
                System.out.println("DOB verification successful");
                continue;
            }
        }

    }

    /**
     * init a transaction
     *
     * @return
     */
    public static TransactionResponse init() {
        TransactionBankAccountRequest request = new TransactionBankAccountRequest();
        request.setReference(System.currentTimeMillis() + "");
        request.setAmount("100");
        request.setCountry("NG");
        request.setCurrency("NGN");
        request.setPayType(PayTypeEnum.bankaccount);
        request.setReturn3dsUrl("http://www.baidu.com");
        request.setBankAccountNumber("2070043686");
        request.setBankCode("033");
        request.setBvn("1234567");
        request.setCustomerPhone("+2341234567890");
        request.setReason("xxxxxxx");
        request.setExpireAt("20");
        request.setDobDay("20");
        request.setDobMonth("05");
        request.setDobYear("2021");
        request.setCallbackUrl("http://wwww.xxx.com/opay/callback");
        System.out.println(request);
        TransactionResponse response = null;
        try {
            response = client.createBankAccountTransaction(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Query transaction status
     *
     * @return
     */
    public static TransactionStatusResponse queryStatus(String reference) {
        TransactionStatusRequest request = new TransactionStatusRequest();
        request.setReference(reference);
        System.out.println(request);
        TransactionStatusResponse response = null;
        try {
            response = client.queryTransactionStatus(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Verify transaction PIN
     *
     * @return
     */
    public static TransactionInputResponse verifyPIN(String reference, String pin) {
        TransactionInputPINRequest request = new TransactionInputPINRequest();
        request.setReference(reference);
        request.setPin(pin);
        System.out.println(request);
        TransactionInputResponse response = null;
        try {
            response = client.verifyTransactionPIN(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Verify transaction OTP
     *
     * @return
     */
    public static TransactionInputResponse verifyOTP(String reference, String otp) {
        TransactionInputOTPRequest request = new TransactionInputOTPRequest();
        request.setReference(reference);
        request.setOtp(otp);
        System.out.println(request);
        TransactionInputResponse response = null;
        try {
            response = client.verifyTransactionOTP(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Verify transaction Phone
     *
     * @return
     */
    public static TransactionInputResponse verifyPhone(String reference, String phone) {
        TransactionInputPhoneRequest request = new TransactionInputPhoneRequest();
        request.setReference(reference);
        request.setPhone(phone);
        System.out.println(request);
        TransactionInputResponse response = null;
        try {
            response = client.verifyTransactionPhone(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Verify transaction Phone
     *
     * @return
     */
    public static TransactionInputResponse verifyDOB(String reference, String dob) {
        TransactionInputDOBRequest request = new TransactionInputDOBRequest();
        request.setReference(reference);
        request.setDob(dob);
        System.out.println(request);
        TransactionInputResponse response = null;
        try {
            response = client.verifyTransactionDOB(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }

}
