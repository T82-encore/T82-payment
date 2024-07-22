package com.T82.payment.domain.model;

import com.T82.payment.domain.request.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Document(collection = "payment_logs")
public class PaymentLog {
    @Id
    private String orderNo;
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
            String payToken,
            Integer amount,
            Long eventId,
            List<PaymentRequest.ItemRequest> list
    ) {
        this.orderNo = orderNo;
        this.payToken = payToken;
        this.amount = amount;
        this.eventId = eventId;
        this.items = list.stream().map(itemRequest -> {
           Item item = new Item();
           item.seatId = itemRequest.getSeatId();
           item.couponId = itemRequest.getCouponId();
           item.amount = itemRequest.getAmount();
           return item;
        }).toList();
    }

    @Getter
    public static class Item {
        private Long seatId;
        private String couponId;
        private Integer amount;
    }
}
