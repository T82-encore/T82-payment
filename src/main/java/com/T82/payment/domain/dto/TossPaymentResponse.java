package com.T82.payment.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class TossPaymentResponse {
    private Integer status;
    private Integer code;
    private String checkoutPage;
    private String payToken;
    private String msg;
    private String errorCode;
    @Setter
    private String orderNo;
}
