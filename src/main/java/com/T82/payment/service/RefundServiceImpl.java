package com.T82.payment.service;

import com.T82.payment.config.util.TossUtil;
import com.T82.payment.domain.dto.TossRefundResponse;
import com.T82.payment.domain.model.PaymentLog;
import com.T82.payment.domain.request.RefundRequest;
import com.T82.payment.domain.response.RefundResponse;
import com.T82.payment.repository.PaymentDAO;
import com.T82.payment.repository.RefundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {
    private final RefundRepository refundRepository;
    private final PaymentDAO paymentDAO;
    private final TossUtil tossUtil;

    @Override
    public RefundResponse refund(RefundRequest refundRequest) {
        PaymentLog paymentLog = paymentDAO.findPaymentLogByOrderNo(refundRequest.getOrderNo());
        TossRefundResponse tossRefundResponse = tossUtil.refund(
                paymentLog.getPayToken(),
                refundRequest.getAmount()
        );
        if (tossRefundResponse.getErrorCode() != null) {
            throw new IllegalArgumentException(tossRefundResponse.getMsg());
        }

        refundRepository.save(refundRequest.toLog(tossRefundResponse));

        return RefundResponse.from(tossRefundResponse);
    }
}
