package com.opay.sdk.utils;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

public class HmacUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HmacUtils.class);
    private static final String HMAC_SHA3_512 = "HMac-SHA3-512";

    static {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            LOGGER.info("security provider BC not found");
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    public static String buildHmacSignature2HexStr(String value, String secret) {
        String result;
        try {
            Mac hmacSHA512 = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(),
                    "HmacSHA512");
            hmacSHA512.init(secretKeySpec);

            byte[] digest = hmacSHA512.doFinal(value.getBytes());
            BigInteger hash = new BigInteger(1, digest);
            result = hash.toString(16);
            if ((result.length() % 2) != 0) {
                result = "0" + result;
            }
        } catch (IllegalStateException | InvalidKeyException | NoSuchAlgorithmException ex) {
            throw new RuntimeException("Problemas calculando HMAC", ex);
        }
        return result;
    }

    public static String buildHmacSignature2Base64Str(String value, String secret) {
        String result;
        try {
            Mac hmacSHA512 = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(),
                    "HmacSHA512");
            hmacSHA512.init(secretKeySpec);

            byte[] digest = hmacSHA512.doFinal(value.getBytes());
            result = Base64.encodeBase64String(digest);
        } catch (IllegalStateException | InvalidKeyException | NoSuchAlgorithmException ex) {
            throw new RuntimeException("Problemas calculando HMAC", ex);
        }

        return result;
    }

    public static String buildHmacSha3_512(String value, String secret) {
        byte[] keyBytes = secret.getBytes();
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA3_512);
        Mac mac = null;

        try {
            mac = Mac.getInstance(HMAC_SHA3_512, "BC");
            mac.init(signingKey);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("no such algorithm ", e);
        } catch (InvalidKeyException e) {
            LOGGER.error("invalid key ", e);
        } catch (NoSuchProviderException e) {
            LOGGER.error("NoSuchProviderException", e);
        }

        byte[] rawHmac = mac.doFinal(value.getBytes());

        String hexBytes = byte2hex(rawHmac);
        return hexBytes;
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp;
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toLowerCase();
    }


}
