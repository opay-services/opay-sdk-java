package com.opay.sdk.common;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HMACSHA512Util {

    public static String hmacSHA512(final String data, final String secureKey) {
        String result = "";
        byte[] bytesKey = secureKey.getBytes();
        final SecretKeySpec secretKey = new SecretKeySpec(bytesKey, "HmacSHA512");
        try {
            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(secretKey);
            final byte[] macData = mac.doFinal(data.getBytes());
            byte[] hex = new Hex().encode(macData);
            result = new String(hex, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String data = "{\"reference\":\"1616661455009\"}";
        System.out.println(hmacSHA512(data, "OPAYPRV16160617080050.388604510744898"));
    }
}
