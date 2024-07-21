package com.T82.payment.domain.request;

import com.T82.payment.domain.dto.TossPaymentResponse;
import com.T82.payment.domain.model.PaymentLog;
import lombok.Getter;

import java.util.List;

public class PaymentRequest {
    @Getter
    private Integer totalAmount;
    @Getter
    private List<ItemRequest> items;

    public PaymentLog toLog(TossPaymentResponse response) {
        return new PaymentLog(
                response.getOrderNo(),
                response.getPayToken(),
                this.totalAmount,
                this.items
        );
    }

    @Getter
    public static class ItemRequest {
        private Long seatId;
        private String couponId;
        private int amount;
    }
}
