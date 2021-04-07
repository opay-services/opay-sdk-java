

<h1 align="center">OPay sdk for java</h1>

<p align="center">
<a href="https://search.maven.org/search?q=g:%22com.opayweb%22%20AND%20a:%22java-sdk-core%22"><img src="https://img.shields.io/maven-central/v/com.opayweb/java-sdk-core.svg?label=Maven%20Central" alt="Latest Stable Version"/></a>
</p>

Welcome to use OPay SDK for Java. OPay SDK for Java allows you to call API without complicated programming. 

This document introduces how to obtain and call OPay SDK for Java.

If you have any problem while using OPay SDK for Java, please [submit an issue](https://github.com/opay-services/opay-sdk-java/issues/new).

## Requirements

- To use OPay SDK for Java, you must have an OPay merchant account as well as an `Merchant ID` and an `AccessKey Secret`. Create and view your AccessKey on the [Merchant Dashboard](https://open.opayweb.com.com "RAM console").

- The OPay Java SDK requires JDK 1.8 or later.

## Installation

If you use Apache Maven to manage Java projects, you only need to add corresponding dependencies to the pom.xml files of the projects.

```xml
<dependency>
     <groupId>com.opayweb</groupId>
     <artifactId>java-sdk-core</artifactId>
     <version>1.0.0-RELEASE</version>
 </dependency>
```

If maven is not downloading jar packages from a central repository, you need to add these dependencies in the pom.xml file, or a NoClassDefFoundError exception will be reported
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.16</version>
</dependency>

<dependency>
    <groupId>com.konghq</groupId>
    <artifactId>unirest-java</artifactId>
    <version>3.11.11</version>
</dependency>

<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.30</version>
</dependency>

<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.73</version>
</dependency>

<dependency>
    <groupId>org.bouncycastle</groupId>
    <artifactId>bcprov-jdk15on</artifactId>
    <version>1.60</version>
    <scope>compile</scope>
</dependency>
```

## Quick Examples

The following code example shows the three main steps to use OPay SDK for Java :

1. Create and initialize a `OPayPaymentClient` instance.

2. Create an API request and set parameters.

3. Initiate the request and handle the response or exceptions.

```java
package com.testprogram;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.ecs.model.v20140526.*;
public class Main {
    public static void main(String[] args) {
       
        //initialize config
        DefaultProfile profile = DefaultProfile.getProfile(Environment.SANDBOX, "<your-merchant-id>", "<your-public-key>", "<your-private-key>");
        //Create and initialize a OPayPaymentClient instance
        OPayPaymentClient client = new OPayPaymentClient(profile);
        //Create an API request and set parameters
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
        // Initiate the request and handle the response or exceptions
        CashierInitializeResponse response = null;
        try {
            response = client.cashierInitialize(request);
        } catch (OPayException e) {
            e.printStackTrace();
        }
    }
    
        
}
```



## Issues
[Opening an Issue](https://github.com/opay-services/opay-sdk-java/issues/new), Issues not conforming to the guidelines may be closed immediately.

## Changelog
Detailed changes for each release are documented in the [release notes](./java-sdk-core/ChangeLog.txt).

## Contribution
Please make sure to read the [Contributing Guide](CONTRIBUTING.md) before making a pull request.

## References
* [Alibaba Cloud Regions & Endpoints](https://developer.aliyun.com/endpoints)
* [Alibab Cloud OpenAPI Portal](https://next.api.aliyun.com/)
* [Latest Release](https://github.com/aliyun/aliyun-openapi-java-sdk)

## License
[Apache-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Copyright (c) 2009-present, Alibaba Cloud All rights reserved.