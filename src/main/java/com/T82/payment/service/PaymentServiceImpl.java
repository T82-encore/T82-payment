package com.T82.payment.service;

import com.T82.payment.api.ApiCoupon;
import com.T82.payment.config.jwt.TokenInfo;
import com.T82.payment.config.kafka.KafkaUtil;
import com.T82.payment.config.util.TossUtil;
import com.T82.payment.domain.dto.TossPaymentDto;
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
    private final ApiCoupon apiCoupon;

    @Override
    public String requestPayment(TokenInfo tokenInfo, PaymentRequest paymentRequest) {
        apiCoupon.verifyCoupon(tokenInfo, paymentRequest);

        TossPaymentDto tossPaymentDto = tossUtil.pay(paymentRequest.getTotalAmount());
        if (tossPaymentDto == null) throw new RuntimeException();
        if (tossPaymentDto.getCode() == -1) throw new IllegalArgumentException();
        if (tossPaymentDto.getCode() == 0) {
            paymentRepository.save(paymentRequest.toLog(tokenInfo, tossPaymentDto));
        }

        return "https://ul.toss.im?scheme=supertoss%3A%2F%2Fpay%3FpayToken%3D" + tossPaymentDto.getPayToken();
    }

    @Override
    public void callbackPayment(CallbackRequest callbackRequest) {
        PaymentLog paymentLog = paymentRepository.findById(callbackRequest.getOrderNo())
                .orElseThrow(() -> new RuntimeException("PaymentLog not found"));

        paymentRepository.save(callbackRequest.updateLog(paymentLog));

        Map<String, Object> paymentMessage = KafkaUtil.getPaymentMessage(paymentLog);
        kafkaProducerService.sendMessage("paymentSuccess", paymentMessage);

        Map<String, Object> couponMessage = KafkaUtil.getCouponMessage(paymentLog);
        kafkaProducerService.sendMessage("couponUsed", couponMessage);
    }
}
