package com.T82.payment.config.kafka;

import com.T82.payment.domain.model.PaymentLog;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KafkaUtil {

    public static Map<String, Object> getPaymentMessage(PaymentLog paymentLog) {
        Map<String, Object> message = new HashMap<>();
        message.put("eventId", paymentLog.getEventId());
        message.put("orderNo", paymentLog.getOrderNo());
        message.put("items", paymentLog.getItems());
        message.put("paymentDate", LocalDateTime.now().toString());

        return message;
    }

    public static Map<String, Object> getCouponMessage(PaymentLog paymentLog) {
        Map<String, Object> message = new HashMap<>();
        message.put("userId", paymentLog.getUserId());
        List<String> list = new ArrayList<>();
        paymentLog.getItems().forEach(item -> {
            if (item.getCouponIds() != null) {
                list.addAll(item.getCouponIds());
            }
        });
        message.put("couponIds", list);

        return message;
    }
}
