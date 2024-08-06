package com.T82.payment.config.util;

import com.T82.payment.domain.dto.TossPaymentDto;
import com.T82.payment.domain.dto.TossRefundDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TossUtil {
    private final String apiKey;
    private final String callbackUrl;
    private final String retAppScheme;

    public TossPaymentDto pay(int amount) {
        URL url = null;
        URLConnection connection = null;
        StringBuilder responseBody = new StringBuilder();
        String orderNo = null;
        String expiredTime = null;

        try {
            url = new URL("https://pay.toss.im/api/v2/payments");
            connection = url.openConnection();
            connection.addRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            orderNo = UUID.randomUUID().toString();
            expiredTime = LocalDateTime.now().plusMinutes(5).toString().split("\\.")[0].replace("T", " ");

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("orderNo", orderNo);
            jsonBody.put("amount", amount);
            jsonBody.put("amountTaxFree", 0);
            jsonBody.put("productDesc", "테스트 결제");
            jsonBody.put("apiKey", apiKey);
            jsonBody.put("autoExecute", true);
            jsonBody.put("resultCallback", callbackUrl);
            jsonBody.put("callbackVersion", "V2");
            jsonBody.put("retUrl", "http://localhost");
            jsonBody.put("retCancelUrl", "http://YOUR-SITE.COM/close");
            jsonBody.put("retAppScheme", retAppScheme);
            jsonBody.put("expiredTime", expiredTime);

            BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());

            bos.write(jsonBody.toString().getBytes(StandardCharsets.UTF_8));
            bos.flush();
            bos.close();


            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line = null;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                responseBody.append(line);
            }
            br.close();

            ObjectMapper objectMapper = new ObjectMapper();
            TossPaymentDto paymentResponse = objectMapper.readValue(responseBody.toString(), TossPaymentDto.class);
            paymentResponse.setOrderNo(orderNo);

            return paymentResponse;
        } catch (Exception e) {
            System.out.println("aa");
            System.out.println(e.getMessage());
            responseBody.append(e);
        }
        System.out.println(responseBody.toString());
        return null;
    }

    public TossRefundDto refund(String payToken, Integer amount) {
        URL url = null;
        URLConnection connection = null;
        StringBuilder responseBody = new StringBuilder();
        try {
            url = new URL("https://pay.toss.im/api/v2/refunds");
            connection = url.openConnection();
            connection.addRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("payToken", payToken);
            jsonBody.put("amount", amount);
            jsonBody.put("apiKey", "sk_test_w5lNQylNqa5lNQe013Nq");

            BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());

            bos.write(jsonBody.toString().getBytes(StandardCharsets.UTF_8));
            bos.flush();
            bos.close();


            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line = null;
            while ((line = br.readLine()) != null) {
                responseBody.append(line);
            }
            br.close();
            System.out.println(responseBody.toString());

            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(responseBody.toString(), TossRefundDto.class);
        } catch (Exception e) {
            responseBody.append(e);
        }
        System.out.println(responseBody.toString());
        return null;
    }

    public TossUtil(
            @Value("${toss.apiKey}") String apiKey,
            @Value("${toss.callbackUrl}") String callbackUrl,
            @Value("${toss.retAppScheme}") String retAppScheme
    ){
        this.apiKey = apiKey;
        this.callbackUrl = callbackUrl;
        this.retAppScheme = retAppScheme;
    }
}
