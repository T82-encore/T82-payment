package com.T82.payment.repository;

import com.T82.payment.domain.model.RefundLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RefundRepository extends MongoRepository<RefundLog, String> {
}
