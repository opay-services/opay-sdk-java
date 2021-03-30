package com.opay.java.sdk.samples.cashier;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.CashierPayMethodEnum;
import com.opay.sdk.enums.CashierPayTypeEnum;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.request.CashierInitializeRequest;
import com.opay.sdk.model.response.CashierInitializeResponse;
import org.apache.http.util.Asserts;

import java.util.ArrayList;
import java.util.List;

/**
 * This sample is to initialize a transaction
 */
public class CashierInitializeSamples {

    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        CashierInitializeSamples samples = new CashierInitializeSamples();
        CashierInitializeResponse response = samples.init();
        Asserts.notNull(response, "response");
        System.out.println(response);
        if (response.success()) {
            System.out.println("Successfully initialized the transaction");
        } else {
            System.out.println("Failed to initialize transaction");
        }

    }

    public CashierInitializeResponse init() {
        CashierInitializeRequest request = new CashierInitializeRequest();
        request.setReference(System.currentTimeMillis() + "");
        request.setMchShortName("Test");
        request.setProductDesc("The best wireless earphone in history");
        request.setProductName("IPhone");
        request.setUserPhone("+2348161564736");
        request.setUserRequestIp("123.4.5.6");
        request.setAmount("100");
        request.setCurrency("NGN");
        List<CashierPayTypeEnum> payTypes = new ArrayList();
        payTypes.add(CashierPayTypeEnum.BalancePayment);
        payTypes.add(CashierPayTypeEnum.OWealth);
        payTypes.add(CashierPayTypeEnum.BonusPayment);
        request.setPayTypes(payTypes);
        List<CashierPayMethodEnum> payMethods = new ArrayList();
        payMethods.add(CashierPayMethodEnum.account);
        payMethods.add(CashierPayMethodEnum.qrcode);
        payMethods.add(CashierPayMethodEnum.bankCard);
        request.setPayMethods(payMethods);
        request.setCallbackUrl("http://www.baidu.com");
        request.setReturnUrl("http://www.baidu.com");
        request.setExpireAt("10");
        System.out.println(request);
        CashierInitializeResponse response = null;
        try {
            response = client.cashierInitialize(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        return response;

    }
}
