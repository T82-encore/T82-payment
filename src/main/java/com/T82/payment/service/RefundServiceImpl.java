package com.T82.payment.service;

import com.T82.payment.repository.PaymentDAO;
import com.T82.payment.repository.RefundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {
    private final RefundRepository refundRepository;
    private final PaymentDAO paymentDAO;
}
