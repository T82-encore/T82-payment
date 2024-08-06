package com.T82.payment.domain.request;

import com.T82.payment.domain.model.PaymentLog;
import lombok.Getter;

@Getter
public class CallbackRequest {
    private String status;
    private String payToken;
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
    @Override
    public String toString() {
        return "CallbackRequest [status=" + status + ", payToken=" + payToken + ", orderNo=" + orderNo;
    }
}
