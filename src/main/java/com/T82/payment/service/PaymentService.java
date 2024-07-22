package com.T82.payment.service;

import com.T82.payment.config.jwt.TokenInfo;
import com.T82.payment.domain.request.CallbackRequest;
import com.T82.payment.domain.request.PaymentRequest;

public interface PaymentService {
    String requestPayment(TokenInfo tokenInfo, PaymentRequest paymentRequest);
    void callbackPayment(CallbackRequest callbackRequest);
}
