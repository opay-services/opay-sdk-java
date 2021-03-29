package com.opay.sdk.utils;

import com.opay.sdk.common.Endpoints;
import com.opay.sdk.model.response.BalanceResponse;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.HashMap;
import java.util.Map;

public class UnirestUtils {

    public static final String MERCHANT_ID = "256621031818649";
    public static final String PUBLIC_KEY = "OPAYPUB16160617080040.6961247863423267";
    public static final String PRIVATE_KEY = "OPAYPRV16160617080050.388604510744898";

    public static <T> T post(String url, Object body, Map<String, String> headers, Class<T> clazz) {
        HttpResponse response = Unirest.post(url)
                .headers(headers)
                .body(body)
                .asObject(clazz);
        return (T) response.getBody();
    }


    public static void main(String[] args) {
        String url = "http://sandbox-cashierapi.opayweb.com" + Endpoints.OPAY_INQUIRY_BALANCE;
        Map<String, String> headers = new HashMap<>();
        headers.put("MerchantId", MERCHANT_ID);
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + PUBLIC_KEY);

        BalanceResponse response = post(url, null, headers, BalanceResponse.class);
        System.out.println(response);
    }
}
