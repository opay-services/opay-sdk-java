package com.opay.sdk.utils;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.Map;

public class UnirestUtils {

    public static <T> T post(String url, Object body, Map<String, String> headers, Class<T> clazz) {
        HttpResponse response = Unirest.post(url)
                .headers(headers)
                .body(body)
                .asObject(clazz);
        return (T) response.getBody();
    }
}
