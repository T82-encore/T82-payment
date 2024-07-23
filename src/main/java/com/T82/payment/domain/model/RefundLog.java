package com.T82.payment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Document(collection = "refund_logs")
public class RefundLog {
    @Id
    private String refundNo;
    private String payToken;
    private Integer refundedAmount;
    private String approvalTime;
    private String transactionId;
    private String refundReason;
}
