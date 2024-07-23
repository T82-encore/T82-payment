package com.T82.payment.domain.request;

import com.T82.payment.config.jwt.TokenInfo;
import com.T82.payment.domain.dto.TossPaymentDto;
import com.T82.payment.domain.model.PaymentLog;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PaymentRequest {
    private Integer totalAmount;
    private Long eventId;
    private List<ItemRequest> items;

    public PaymentLog toLog(TossPaymentDto response) {
        return new PaymentLog(
                response.getOrderNo(),
                response.getPayToken(),
                this.totalAmount,
                this.eventId,
                this.items
        );
    }

    public CouponVerifyRequest convertToCouponVerifyRequest(TokenInfo tokenInfo) {
        List<CouponVerifyRequest.ItemRequest> itemRequests = this.items.stream()
                .filter(item -> item.getCouponIds() != null && item.getBeforeAmount() != null)
                .map(item -> new CouponVerifyRequest.ItemRequest(
                        item.getCouponIds(),
                        item.getBeforeAmount()
                ))
                .collect(Collectors.toList());

        return new CouponVerifyRequest(tokenInfo.id().toString(), itemRequests);
    }

    @Getter
    public static class ItemRequest {
        private Long seatId;
        private List<String> couponIds;
        private Integer beforeAmount;
        private int amount;
    }
}
