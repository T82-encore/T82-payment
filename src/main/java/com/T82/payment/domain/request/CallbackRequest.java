package com.T82.payment.domain.request;

import com.T82.payment.domain.model.PaymentLog;
import lombok.Getter;

public class CallbackRequest {
    private String status;
    private String payToken;
    @Getter
    private String orderNo;
    private String payMethod;
    private String paidTs;
    private String transactionId;

    public PaymentLog updateLog(PaymentLog paymentLog) {
        paymentLog.setPayMethod(this.payMethod);
        paymentLog.setPaidTs(this.paidTs);
        paymentLog.setTransactionId(this.transactionId);
        return paymentLog;
    }
}
