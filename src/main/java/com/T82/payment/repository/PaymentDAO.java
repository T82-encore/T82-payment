package com.T82.payment.repository;

import com.T82.payment.domain.model.PaymentLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentDAO {
    private final PaymentRepository paymentLogRepository;

    public PaymentLog findPaymentLogByOrderNo(String orderNo) {
        return paymentLogRepository.findById(orderNo)
                .orElse(null);
    }
}
