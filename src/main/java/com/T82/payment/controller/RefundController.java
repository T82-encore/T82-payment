package com.T82.payment.controller;

import com.T82.payment.service.RefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class RefundController {
    private final RefundService refundService;
}
