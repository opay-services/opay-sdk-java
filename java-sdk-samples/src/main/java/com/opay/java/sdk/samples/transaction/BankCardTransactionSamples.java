package com.opay.java.sdk.samples.transaction;

import com.alibaba.fastjson.JSONObject;
import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.enums.PayTypeEnum;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.TransactionStatus;
import com.opay.sdk.model.request.*;
import com.opay.sdk.model.response.TransactionInputResponse;
import com.opay.sdk.model.response.TransactionResponse;
import com.opay.sdk.model.response.TransactionStatusResponse;
import com.opay.sdk.model.response.TransferStatusResponse;

/**
 * Sample of transfer to Bank Account
 */
public class BankCardTransactionSamples {

    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {


        TransactionResponse response = init();
        if (response == null) {
            System.out.println("Failed to initialize transaction");
            return;
        }
        System.out.println("Initialize transaction response body:" + response);
        if (!response.success()) {
            System.out.println("Failed to initialize transaction:" + response.getMessage());
            return;
        }
        String reference = response.getData().getReference();
        System.out.println("Successfully initialized the transaction");
        while (true) {
            TransactionStatusResponse statusResponse = queryStatus(reference);
            if (statusResponse == null) {
                System.out.println("Failed to query transaction status");
                return;
            }
            System.out.println("Query status response body:" + statusResponse);

            if (!statusResponse.success()) {
                System.out.println("Failed to query transaction status:" + statusResponse.getMessage());
                return;
            }

            TransactionStatus data = statusResponse.getData();
            if (com.opay.sdk.enums.TransactionStatus.PENDING.getValue().equals(data.getStatus()) || com.opay.sdk.enums.TransactionStatus.INITIAL.getValue().equals(data.getStatus())) {
                System.out.println("Transaction processing");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (com.opay.sdk.enums.TransactionStatus.SUCCESS.getValue().equals(data.getStatus())) {
                System.out.println("Successful transaction");
                break;
            }
            if (com.opay.sdk.enums.TransactionStatus.INPUT_PIN.getValue().equals(data.getStatus())) {
                TransactionInputResponse inputResponse = verifyPIN(reference, "1105");
                if (inputResponse.success()) {
                    System.out.println("PIN verification successful");
                    continue;
                } else {
                    System.out.println("PIN verification failed:" + inputResponse.getMessage());
                    break;
                }
            }
            if (com.opay.sdk.enums.TransactionStatus.INPUT_OTP.getValue().equals(data.getStatus())) {
                TransactionInputResponse inputResponse = verifyOTP(reference, "543210");
                if (inputResponse.success()) {
                    System.out.println("OTP verification successful");

                    continue;
                } else {
                    System.out.println("OTP verification failed:" + inputResponse.getMessage());
                    break;
                }
            }
            if (com.opay.sdk.enums.TransactionStatus.INPUT_PHONE.getValue().equals(data.getStatus())) {
                TransactionInputResponse inputResponse = verifyPhone(reference, "+2341234567890");
                if (inputResponse.success()) {
                    System.out.println("Phone verification successful");
                    continue;
                } else {
                    System.out.println("Phone verification failed:" + inputResponse.getMessage());
                    break;
                }
            }
            if (com.opay.sdk.enums.TransactionStatus.INPUT_DOB.getValue().equals(data.getStatus())) {
                TransactionInputResponse inputResponse = verifyDOB(reference, "11/11/11");
                if (inputResponse.success()) {
                    System.out.println("DOB verification successful");
                    continue;
                } else {
                    System.out.println("DOB verification failed:" + inputResponse.getMessage());
                    break;
                }
            }
        }

    }

    /**
     * init a transaction
     *
     * @return
     */
    public static TransactionResponse init() {
        TransactionBankCardRequest request = new TransactionBankCardRequest();
        request.setReference(System.currentTimeMillis() + "");
        request.setAmount("100");
        request.setCountry("NG");
        request.setCurrency("NGN");
        request.setPayType(PayTypeEnum.bankcard);
        request.setFirstName("wanjun");
        request.setLastName("an");
        request.setCustomerEmail("anwj@163.com");
        request.setCardDateMonth("12");
        request.setCardDateYear("22");
        request.setCardCVC("561");
        request.setReturn3dsUrl("http://www.baidu.com");
        request.setCardNumber("5061460410121111105");
        request.setBankCode("033");
        request.setReason("xxxxxxx");
        request.setExpireAt("20");
        request.setBillingZip("");
        request.setBillingCity("");
        request.setBillingAddress("");
        request.setBillingState("");
        request.setBillingCountry("");
        System.out.println(JSONObject.toJSONString(request));
        TransactionResponse response = null;
        try {
            response = client.createBankCardTransaction(request);
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
        TransactionInputResponse response = null;
        try {
            response = client.verifyTransactionDOB(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }


    /**
     * The second step is to query the transfer order status
     *
     * @param reference Merchant transaction number
     * @return
     */
    public static TransferStatusResponse step2(String reference) {
        TransferStatusRequest request = new TransferStatusRequest();
        System.out.println(reference);
        request.setReference(reference);
        TransferStatusResponse response = null;
        try {
            response = client.queryTransferToBanktatus(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;
    }
}
