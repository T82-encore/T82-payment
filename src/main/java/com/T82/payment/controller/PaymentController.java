package com.T82.payment.controller;

import com.T82.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/refund")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
}
