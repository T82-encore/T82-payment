package com.T82.payment.service;

import com.T82.payment.config.util.TossUtil;
import com.T82.payment.domain.dto.TossPaymentResponse;
import com.T82.payment.domain.request.PaymentRequest;
import com.T82.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final TossUtil tossUtil;

    @Override
    public String requestPayment(PaymentRequest paymentRequest) {
        System.out.println(paymentRequest.getItems().size());
        TossPaymentResponse tossPaymentResponse = tossUtil.pay(paymentRequest.getTotalAmount());
        if (tossPaymentResponse == null) throw new RuntimeException();
        if (tossPaymentResponse.getCode() == -1) throw new IllegalArgumentException();
        if (tossPaymentResponse.getCode() == 0) {
            paymentRepository.save(paymentRequest.toLog(tossPaymentResponse));
        }
        return "https://ul.toss.im?scheme=supertoss%3A%2F%2Fpay%3FpayToken%3D" + tossPaymentResponse.getPayToken();
    }
}
