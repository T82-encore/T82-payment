package com.T82.payment.domain.request;

import java.util.List;

public class PaymentRequest {
    private Integer totalAmount;
    private List<ItemRequest> items;

    public static class ItemRequest {
        private Long seatId;
        private String couponId;
        private int amount;
    }
}
