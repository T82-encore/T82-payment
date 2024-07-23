package com.T82.payment.domain.model;

import com.T82.payment.domain.request.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Document(collection = "payment_logs")
public class PaymentLog {
    @Id
    private String orderNo;
    private UUID userId;
    private String payToken;
    @Setter
    private String payMethod;
    private Integer amount;
    @Setter
    private String paidTs;
    @Setter
    private String transactionId;
    private Long eventId;
    private List<Item> items;

    public PaymentLog(
            String orderNo,
            UUID userId,
            String payToken,
            Integer amount,
            Long eventId,
            List<PaymentRequest.ItemRequest> list
    ) {
        this.orderNo = orderNo;
        this.userId = userId;
        this.payToken = payToken;
        this.amount = amount;
        this.eventId = eventId;
        this.items = list.stream().map(itemRequest -> {
           Item item = new Item();
           item.seatId = itemRequest.getSeatId();
           item.couponIds = itemRequest.getCouponIds();
           item.amount = itemRequest.getAmount();
           return item;
        }).toList();
    }

    @Getter
    public static class Item {
        private Long seatId;
        private List<String> couponIds;
        private Integer amount;
    }
}
