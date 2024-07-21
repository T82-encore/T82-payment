package com.T82.payment.domain.model;

import com.T82.payment.domain.request.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@Getter
@Document(collection = "payment_logs")
public class PaymentLog {
    @Id
    private String orderNo;
    private String payToken;
    private String payMethod;
    private Integer amount;
    private String paidTs;
    private String transactionId;
    private List<Item> items;

    public PaymentLog(
            String orderNo,
            String payToken,
            Integer amount,
            List<PaymentRequest.ItemRequest> list
    ) {
        this.orderNo = orderNo;
        this.payToken = payToken;
        this.amount = amount;
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
