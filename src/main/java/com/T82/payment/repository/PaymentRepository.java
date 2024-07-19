package com.T82.payment.repository;

import com.T82.payment.domain.model.PaymentLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<PaymentLog, String> {
}
