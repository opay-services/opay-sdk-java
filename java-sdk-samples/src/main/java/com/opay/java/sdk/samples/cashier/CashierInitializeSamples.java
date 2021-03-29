package com.opay.java.sdk.samples.cashier;

import com.opay.java.sdk.samples.Config;
import com.opay.sdk.OPayPaymentClient;
import com.opay.sdk.common.DefaultProfile;
import com.opay.sdk.enums.CashierPayMethod;
import com.opay.sdk.enums.CashierPayType;
import com.opay.sdk.enums.Environment;
import com.opay.sdk.exception.OPayException;
import com.opay.sdk.model.request.CashierInitializeRequest;
import com.opay.sdk.model.response.CashierInitializeResponse;

import java.util.ArrayList;
import java.util.List;

public class CashierInitializeSamples {

    private static DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, Config.MERCHANT_ID, Config.PUBLIC_KEY, Config.PRIVATE_KEY);

    private static OPayPaymentClient client = new OPayPaymentClient(profile);

    public static void main(String[] args) {
        CashierInitializeRequest request =new CashierInitializeRequest();
        request.setReference(System.currentTimeMillis() + "");
        request.setMchShortName("Test");
        request.setProductDesc("The best wireless earphone in history");
        request.setProductName("IPhone");
        request.setUserPhone("+2348161564736");
        request.setUserRequestIp("123.4.5.6");
        request.setAmount("100");
        request.setCurrency("NGN");
        List<CashierPayType> payTypes = new ArrayList();
        payTypes.add(CashierPayType.BalancePayment);
        payTypes.add(CashierPayType.OWealth);
        payTypes.add(CashierPayType.BonusPayment);
        request.setPayTypes(payTypes);
        List<CashierPayMethod> payMethods = new ArrayList();
        payMethods.add(CashierPayMethod.account);
        payMethods.add(CashierPayMethod.qrcode);
        payMethods.add(CashierPayMethod.bankCard);
        request.setPayMethods(payMethods);
        request.setCallbackUrl("http://www.baidu.com");
        request.setReturnUrl("http://www.baidu.com");
        request.setExpireAt("10");
        CashierInitializeResponse response = null;
        try {
            response = client.cashierInitialize(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
        if(response.success()){
            System.out.println(response);
        }else{
            System.out.println(String.format("code:%s, message:%s", response.getCode(), response.getMessage()));
        }

    }
}
