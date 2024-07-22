package com.T82.payment.config.kafka;

import com.T82.payment.domain.model.PaymentLog;

import java.time.LocalDateTime;
import java.util.HashMap;
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
}
