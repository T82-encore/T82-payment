package com.T82.payment.service;

import com.T82.payment.domain.request.CallbackRequest;
import com.T82.payment.domain.request.PaymentRequest;

public interface PaymentService {
    String requestPayment(PaymentRequest paymentRequest);
    void callbackPayment(CallbackRequest callbackRequest);
}
