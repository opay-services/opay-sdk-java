package com.opay.sdk;

import com.alibaba.fastjson.JSONObject;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.common.Endpoints;
import com.opay.sdk.common.HMACSHA512Util;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.request.*;
import com.opay.sdk.model.response.*;
import com.opay.sdk.utils.UnirestUtils;
import kong.unirest.UnirestException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class OPayPaymentClient {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static String BASE_URL = "";
    private Map<String, String> headers = new HashMap<>();
    private DefaultProfile profile;

    public OPayPaymentClient(DefaultProfile profile) {
        synchronized (this) {
            this.profile = profile;
            if (profile.getEnvironment().equals(Environment.SANDBOX)) {
                BASE_URL = "http://sandbox-cashierapi.opayweb.com";
            } else {
                BASE_URL = "http://cashierapi.opayweb.com";
            }
            headers.put("MerchantId", profile.getMerchantId());
            headers.put("Content-Type", "application/json");
        }
    }


    /**
     * Call the cashier/initialize endpoint to generate the OPay’s unique one-time checkout URL.
     * This endpoint expects one product/order to be passed in the request for checkout page.
     * Where more than one product needs checking out, combine the products into one order before
     * calling the cashier/initialize endpoint.
     *
     * @param request
     * @return
     */
    public CashierInitializeResponse cashierInitialize(CashierInitializeRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        CashierInitializeResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_CASHIER_INITIALIZE;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + profile.getPublicKey());
            response = UnirestUtils.post(url, request, headers, CashierInitializeResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * Query transaction status(Status contains INITIAL, PENDING, SUCCESS, FAIL, CLOSE)
     *
     * @param request
     * @return
     */
    public CashierStatusResponse cashierStatus(CashierStatusRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        CashierStatusResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_CASHIER_STATUS;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + signature(request));
            response = UnirestUtils.post(url, request, headers, CashierStatusResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }


    /**
     * When the transaction state is the initial state, you can call the method to close the transaction.
     *
     * @param request
     * @return
     */
    public CashierCloseResponse cashierClose(CashierCloseRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        CashierCloseResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_CASHIER_CLOSE;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + signature(request));
            response = UnirestUtils.post(url, request, headers, CashierCloseResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * After the transaction is successful, if the customer wants to refund,
     * they can call the method to initiate a refund request
     *
     * @param request
     * @return
     */
    public CashierRefundResponse cashierRefund(CashierRefundRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        CashierRefundResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_CASHIER_REFUND;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + signature(request));
            response = UnirestUtils.post(url, request, headers, CashierRefundResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    public CashierRefundStatusResponse cashierRefundStatus(CashierRefundStatusRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        CashierRefundStatusResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_CASHIER_REFUND_STATUS;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + signature(request));
            response = UnirestUtils.post(url, request, headers, CashierRefundStatusResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * Transfer to OPay wallet account
     *
     * @param request
     * @return
     */
    public TransferResponse transferToWallet(TransferToWalletRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        TransferResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TRANSFER_TO_WALLET;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + signature(request));
            response = UnirestUtils.post(url, request, headers, TransferResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * Transfer to bank account
     *
     * @param request
     * @return
     */
    public TransferResponse transferToBankAccount(TransferToBankRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        TransferResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TRANSFER_TO_BANK;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + signature(request));
            response = UnirestUtils.post(url, request, headers, TransferResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * Query Wallet Transfer Status
     *
     * @param request
     * @return
     */
    public TransferStatusResponse queryTransferToWalletStatus(TransferStatusRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        TransferStatusResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TRANSFER_TO_WALLET_STATUS;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + signature(request));
            response = UnirestUtils.post(url, request, headers, TransferStatusResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * Query Bank Account Transfer Status
     *
     * @param request
     * @return
     */
    public TransferStatusResponse queryTransferToBanktatus(TransferStatusRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        TransferStatusResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TRANSFER_TO_BANK_STATUS;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + signature(request));
            response = UnirestUtils.post(url, request, headers, TransferStatusResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * Initialize a transaction.
     *
     * @param request
     * @return
     */
    public TransactionResponse transactionInitialize(TransactionRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        TransactionResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TRANSACTION_BANK_TRANSFER_INITIALIZE;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + signature(request));
            response = UnirestUtils.post(url, request, headers, TransactionResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }


    /**
     * Initialize a bank card transaction.
     *
     * @param request
     * @return
     */
    public TransactionResponse createBankCardTransaction(TransactionBankCardRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        TransactionResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TRANSACTION_INITIALIZE;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + profile.getPublicKey());
            response = UnirestUtils.post(url, request, headers, TransactionResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * Initialize a bank account transaction.
     *
     * @param request
     * @return
     */
    public TransactionResponse createBankAccountTransaction(TransactionBankAccountRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        TransactionResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TRANSACTION_INITIALIZE;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + profile.getPublicKey());
            response = UnirestUtils.post(url, request, headers, TransactionResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * When the status is "PENDING" or the creation of a transaction is abnormal,
     * call this method to view the transaction status. Please wait 10 minutes or more
     * and then check whether the status has changed. Don't call it too early.
     *
     * @param request
     * @return
     */
    public TransactionStatusResponse queryTransactionStatus(TransactionStatusRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        TransactionStatusResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TRANSACTION_STATUS;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + signature(request));
            response = UnirestUtils.post(url, request, headers, TransactionStatusResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * When the status returned by the method of querying the transaction status is "INPUT-OTP",
     * you need to call this method to verify OTP.
     *
     * @param request
     * @return
     */
    public TransactionInputResponse verifyTransactionOTP(TransactionInputOTPRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        TransactionInputResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TRANSACTION_INPUT_OTP;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + profile.getPublicKey());
            response = UnirestUtils.post(url, request, headers, TransactionInputResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * When the status returned by the method of querying the transaction status is "INPUT-PIN",
     * you need to call this method to verify PIN.
     *
     * @param request
     * @return
     */
    public TransactionInputResponse verifyTransactionPIN(TransactionInputPINRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        TransactionInputResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TRANSACTION_INPUT_PIN;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + profile.getPublicKey());
            response = UnirestUtils.post(url, request, headers, TransactionInputResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * When the status returned by the method of querying the transaction status is "INPUT-PHONE",
     * you need to call this method to verify phone.
     *
     * @param request
     * @return
     */
    public TransactionInputResponse verifyTransactionPhone(TransactionInputPhoneRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        TransactionInputResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TRANSACTION_INPUT_PHOME;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + profile.getPublicKey());
            response = UnirestUtils.post(url, request, headers, TransactionInputResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * When the status returned by the method of querying the transaction status is "INPUT-DOB",
     * you need to call this method to verify DOB.
     *
     * @param request
     * @return
     */
    public TransactionInputResponse verifyTransactionDOB(TransactionInputDOBRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        TransactionInputResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TRANSACTION_INPUT_DOB;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + profile.getPublicKey());
            response = UnirestUtils.post(url, request, headers, TransactionInputResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * Use this transaction API to enable and accept payments via bank transfer without utilising
     * the full power of OPay's payment gateway.
     *
     * @param request
     * @return
     */
    public TransactionBankTransferResponse createBankTransferTransaction(TransactionBankTransferRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        TransactionBankTransferResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TRANSACTION_BANK_TRANSFER_INITIALIZE;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + signature(request));
            response = UnirestUtils.post(url, request, headers, TransactionBankTransferResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * Query a bankTransfer transaction's status
     *
     * @param request
     * @return
     */
    public TransactionBankTransferStatusResponse querybankTransferTransactionStatus(TransactionBankTransferStatusRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        TransactionBankTransferStatusResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TRANSACTION_BANK_TRANSFER_STATUS;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + signature(request));
            response = UnirestUtils.post(url, request, headers, TransactionBankTransferStatusResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * Call this method to enable and accept payments via USSD without utilising the full power of OPay's payment gateway.
     *
     * @param request
     * @return
     */
    public TransactionUssdResponse createUssdTransaction(TransactionUssdRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        TransactionUssdResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TRANSACTION_USSD_INITIALIZE;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + signature(request));
            response = UnirestUtils.post(url, request, headers, TransactionUssdResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }


    /**
     * Query a ussd transaction's status.
     *
     * @param request
     * @return
     */
    public TransactionUssdStatusResponse queryUssdTransactionStatus(TransactionStatusRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        TransactionUssdStatusResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TRANSACTION_USSD_STATUS;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + signature(request));
            response = UnirestUtils.post(url, request, headers, TransactionUssdStatusResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }


    /**
     * Query betting providers
     *
     * @return
     */
    public BettingProviderResponse queryBettingProviders() throws OPayException {
        BettingProviderResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_BETTING_PROVIDERS;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            //headers.put(AUTHORIZATION_HEADER, "Bearer " + profile.getPublicKey());
            response = UnirestUtils.post(url, null, headers, BettingProviderResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * Validate the customerId for a specific provider.
     *
     * @return
     */
    public BettingAccountResponse validateBettingCustomerId(BettingAccountRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        BettingAccountResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_BETTING_PROVIDER_VALIDATE;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + profile.getPublicKey());
            response = UnirestUtils.post(url, request, headers, BettingAccountResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * Initiate a betting airtime transaction.
     *
     * @return
     */
    public BettingTopupResponse createBettingTopupTranaction(BettingTopupRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        BettingTopupResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TOPUP_TRANSACTION;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + signature(request));
            response = UnirestUtils.post(url, request, headers, BettingTopupResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * Query betting airtime transaction status.
     *
     * @return
     */
    public BettingTopupStatusResponse queryBettingTopupTranactionStatus(BettingStatusRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        BettingTopupStatusResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TOPUP_STATUS;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + profile.getPublicKey());
            response = UnirestUtils.post(url, request, headers, BettingTopupStatusResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * Initiate a airtime airtime transaction.
     *
     * @return
     */
    public AirtimeTopupResponse createAirtimeTopup(AirtimeTopupRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        AirtimeTopupResponse response = null;
        try {
            String url = BASE_URL + Endpoints.OPAY_TOPUP_TRANSACTION;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + signature(request));
            response = UnirestUtils.post(url, request, headers, AirtimeTopupResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * Query airtime airtime transaction status.
     *
     * @return
     */
    public AirtimeTopupStatusResponse queryAirtimeTopupStatus(AirtimeTopupStatusRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        AirtimeTopupStatusResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TOPUP_STATUS;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + profile.getPublicKey());
            response = UnirestUtils.post(url, request, headers, AirtimeTopupStatusResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * Query OPay account balance
     *
     * @return
     */
    public BalanceResponse balances() throws OPayException {

        BalanceResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_INQUIRY_BALANCE;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + profile.getPublicKey());
            response = UnirestUtils.post(url, null, headers, BalanceResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }


    /**
     * Query bank list
     *
     * @return
     */
    public BankResponse banks(BankRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        BankResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TRANSFER_SUPPORT_BANKS;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + profile.getPublicKey());
            response = UnirestUtils.post(url, null, headers, BankResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * Query country list
     *
     * @return
     */
    public CountryResponse countries() throws OPayException {
        CountryResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_TRANSFER_SUPPORT_COUNTRIES;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + profile.getPublicKey());
            response = UnirestUtils.post(url, null, headers, CountryResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }

    /**
     * Query merchant info
     *
     * @return
     */
    public MerchantInfoResponse merchantInfo(MerchantInfoRequest request) throws OPayException {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        MerchantInfoResponse response;
        try {
            String url = BASE_URL + Endpoints.OPAY_INQUIRY_MERCHANT;
            Map<String, String> headers = new HashMap<>();
            headers.putAll(this.headers);
            headers.put(AUTHORIZATION_HEADER, "Bearer " + profile.getPublicKey());
            response = UnirestUtils.post(url, request, headers, MerchantInfoResponse.class);
        } catch (UnirestException e) {
            throw new OPayException(e);
        }
        return response;
    }


    private String signature(Object object) {
        String data = JSONObject.toJSONString(object);
        //System.out.println("加密前：" + data);
        String signature = HMACSHA512Util.hmacSHA512(data, this.profile.getPrivateKey());
        //System.out.println("签名：" + signature);
        return signature;
    }
}
