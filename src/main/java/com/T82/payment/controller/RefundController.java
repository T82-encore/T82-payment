package com.T82.payment.controller;

import com.T82.payment.domain.request.RefundRequest;
import com.T82.payment.domain.response.RefundResponse;
import com.T82.payment.service.RefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/refund")
@RequiredArgsConstructor
public class RefundController {
    private final RefundService refundService;

    @PostMapping
    public RefundResponse refund(@RequestBody RefundRequest refundRequest) {
        return refundService.refund(refundRequest);
    }
}
