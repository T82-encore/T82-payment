package com.T82.payment.service;

import com.T82.payment.config.kafka.KafkaUtil;
import com.T82.payment.config.util.TossUtil;
import com.T82.payment.domain.dto.TossPaymentResponse;
import com.T82.payment.domain.model.PaymentLog;
import com.T82.payment.domain.request.CallbackRequest;
import com.T82.payment.domain.request.PaymentRequest;
import com.T82.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final TossUtil tossUtil;
    private final KafkaProducerService kafkaProducerService;

    @Override
    public String requestPayment(PaymentRequest paymentRequest) {
        TossPaymentResponse tossPaymentResponse = tossUtil.pay(paymentRequest.getTotalAmount());
        if (tossPaymentResponse == null) throw new RuntimeException();
        if (tossPaymentResponse.getCode() == -1) throw new IllegalArgumentException();
        if (tossPaymentResponse.getCode() == 0) {
            paymentRepository.save(paymentRequest.toLog(tossPaymentResponse));
        }
        return "https://ul.toss.im?scheme=supertoss%3A%2F%2Fpay%3FpayToken%3D" + tossPaymentResponse.getPayToken();
    }

    @Override
    public void callbackPayment(CallbackRequest callbackRequest) {
        PaymentLog paymentLog = paymentRepository.findById(callbackRequest.getOrderNo())
                .orElseThrow(() -> new RuntimeException("PaymentLog not found"));

        paymentRepository.save(callbackRequest.updateLog(paymentLog));

        Map<String, Object> message = KafkaUtil.getPaymentMessage(paymentLog);
        kafkaProducerService.sendMessage("payment_success", message);
    }
}
