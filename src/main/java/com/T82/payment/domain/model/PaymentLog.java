package com.T82.payment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@Getter @Setter
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

    @Getter @Setter
    public static class Item {
        private Long seatId;
        private Long couponId;
        private Integer amount;
    }
}
