package com.T82.payment.controller;

import com.T82.payment.domain.request.PaymentRequest;
import com.T82.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public String requestPayment(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.requestPayment(paymentRequest);
    }
}
