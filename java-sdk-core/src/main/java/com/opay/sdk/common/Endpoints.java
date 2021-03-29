package com.opay.sdk.common;


/**
 * Endpoint List
 *
 * @author anwanjun
 * @since 2021/03/19
 */
public class Endpoints {

    //CASHIER ENDPOINTS
    public static String OPAY_CASHIER_INITIALIZE = "/api/v3/cashier/initialize";
    public static String OPAY_CASHIER_STATUS = "/api/v3/cashier/status";
    public static String OPAY_CASHIER_CLOSE = "/api/v3/cashier/close";
    public static String OPAY_CASHIER_REFUND = "/api/v3/cashier/refund";
    public static String OPAY_CASHIER_REFUND_STATUS = "/api/v3/cashier/refund/status";

    //TRANSFER ENDPOINTS
    public static String OPAY_TRANSFER_TO_WALLET = "/api/v3/transfer/toWallet";
    public static String OPAY_TRANSFER_TO_BANK = "/api/v3/transfer/toBank";
    public static String OPAY_TRANSFER_TO_WALLET_STATUS = "/api/v3/transfer/status/toWallet";
    public static String OPAY_TRANSFER_TO_BANK_STATUS = "/api/v3/transfer/status/toBank";
    public static String OPAY_TRANSFER_BATCH_TO_WALLET = "/api/v3/transfer/batchToWallet";
    public static String OPAY_TRANSFER_BATCH_TO_BANK = "/api/v3/transfer/batchToBank";
    public static String OPAY_TRANSFER_BATCH_TO_WALLET_STATUS = "/api/v3/transfer/status/batchToWallet";
    public static String OPAY_TRANSFER_BATCH_TO_BANK_STATUS = "/api/v3/transfer/status/batchToBank";

    // TRANSACTION ENDPOINT
    public static String OPAY_TRANSACTION_INITIALIZE = "/api/v3/transaction/initialize";
    public static String OPAY_TRANSACTION_INPUT_PIN = "/api/v3/transaction/input-pin";
    public static String OPAY_TRANSACTION_INPUT_PHOME = "/api/v3/transaction/input-phone";
    public static String OPAY_TRANSACTION_INPUT_OTP = "/api/v3/transaction/input-otp";
    public static String OPAY_TRANSACTION_INPUT_DOB = "/api/v3/transaction/input-dob";
    public static String OPAY_TRANSACTION_STATUS = "/api/v3/transaction/status";
    public static String OPAY_TRANSACTION_BANKS = "/api/v3/transaction/banks";
    public static String OPAY_TRANSACTION_BANK_TRANSFER_INITIALIZE = "/api/v3/transaction/bankTransfer/initialize";
    public static String OPAY_TRANSACTION_BANK_TRANSFER_STATUS = "/api/v3/transaction/bankTransfer/status";
    public static String OPAY_TRANSACTION_USSD_INITIALIZE = "/api/v3/transaction/ussd/initialize";
    public static String OPAY_TRANSACTION_USSD_STATUS = "/api/v3/transaction/ussd/status";


    // CERTIFICATION PAYMENT ENDPOINT
    public static String OPAY_CERTPAY_INITIALIZE = "/api/v3/certpay/initialize";
    public static String OPAY_CERTPAY_VERIFY_PIN = "/api/v3/certpay/verifyPIN";
    public static String OPAY_CERTPAY_SEND_OTP = "/api/v3/certpay/sendOTP";
    public static String OPAY_CERTPAY_VERIFY_OTP = "/api/v3/certpay/verifyOTP";
    public static String OPAY_CERTPAY_STATUS = "/api/v3/certpay/status";
    public static String OPAY_CERTPAY_CLOSE = "/api/v3/certpay/close";
    public static String OPAY_CERTPAY_REFUND = "/api/v3/certpay/refund";
    public static String OPAY_CERTPAY_REFUND_STATUS = "/api/v3/certpay/refundStatus";


    // CHARGE ENDPOINT
    public static String OPAY_CHARGE_INITIALIZE = "/api/v3/charge/initialize";
    public static String OPAY_CHARGE_STATUS = "/api/v3/charge/status";


    //ENDPOINT FOR TRANSFER COUNTRIES THAT OPAY CURRENTLY SUPPORT
    public static String OPAY_TRANSFER_SUPPORT_COUNTRIES = "/api/v3/countries";

    //ENDPOINT FOR BANKS THAT OPAY CURRENTLY SUPPORTS
    public static String OPAY_TRANSFER_SUPPORT_BANKS = "/api/v3/banks";

    //ENDPOINT FOR THE BALANCES OF ALL YOUR OPAY ACCOUNTS
    public static String OPAY_INQUIRY_BALANCE = "/api/v3/balances";

    // ENDPOINT TO VALIDATE(QUERY) OPAY USER
    public static String OPAY_USER_CREATE = "/api/v3/info/user/create";
    public static String OPAY_USER_INQUIRY = "/api/v3/info/user";
    public static String OPAY_USER_UPDATE = "/api/v3/info/user/update";
    public static String OPAY_SEND_OTP = "/api/v3/info/user/sendOTP";

    // ENDPOINT TO VALIDATE(QUERY) OPAY MERCHANT
    public static String OPAY_INQUIRY_MERCHANT = "/api/v3/info/merchant";

    public static String OPAY_BETTING_PROVIDERS = "/api/v3/bills/betting-providers";
    public static String OPAY_BETTING_PROVIDER_VALIDATE = "/api/v3/bills/validate";
    public static String OPAY_TOPUP_TRANSACTION = "/api/v3/bills/bulk-bills";
    public static String OPAY_TOPUP_STATUS = "/api/v3/bills/bulk-status";

}