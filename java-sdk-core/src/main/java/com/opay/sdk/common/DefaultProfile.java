package com.opay.sdk.common;

import com.opay.sdk.enums.Environment;
import lombok.Data;

@Data
public class DefaultProfile {

    private Environment environment;

    private String merchantId;

    private String publicKey;

    private String privateKey;

    private DefaultProfile(Environment environment, String merchantId, String publicKey, String privateKey) {
        this.environment = environment;
        this.merchantId = merchantId;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public static DefaultProfile getProfile(Environment environment, String merchantId, String publicKey, String privateKey) {
        DefaultProfile profile = new DefaultProfile(environment, merchantId, publicKey, privateKey);
        return profile;
    }
}
