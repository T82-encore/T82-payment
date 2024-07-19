package com.T82.payment.service;

import com.T82.payment.domain.request.PaymentRequest;

public interface PaymentService {
    String requestPayment(PaymentRequest paymentRequest);
}
