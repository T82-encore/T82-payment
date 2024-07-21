package com.T82.payment.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TossRefundResponse {
    private String refundNo;
    private int refundedAmount;
    private String approvalTime;
    private String payToken;
    private String transactionId;
    private String msg;
    private String errorCode;
}
