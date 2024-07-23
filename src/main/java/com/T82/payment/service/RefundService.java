package com.T82.payment.service;

import com.T82.payment.domain.request.RefundRequest;
import com.T82.payment.domain.response.RefundResponse;

public interface RefundService {
    RefundResponse refund(RefundRequest refundRequest);
}
