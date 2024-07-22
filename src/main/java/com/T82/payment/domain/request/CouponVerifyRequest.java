package com.T82.payment.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CouponVerifyRequest {
    private String userId;
    private List<ItemRequest> items;

    @Getter
    @AllArgsConstructor
    public static class ItemRequest {
        private List<String> couponIds;
        private int beforeAmount;
    }
}
