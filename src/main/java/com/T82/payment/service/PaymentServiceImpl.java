package com.T82.payment.service;

import com.T82.payment.domain.request.PaymentRequest;
import com.T82.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public String requestPayment(PaymentRequest paymentRequest) {
        return "";
    }
}
