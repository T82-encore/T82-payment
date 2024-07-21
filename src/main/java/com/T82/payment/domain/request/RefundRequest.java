package com.T82.payment.domain.request;

import com.T82.payment.domain.dto.TossRefundResponse;
import com.T82.payment.domain.model.RefundLog;
import lombok.Getter;

public class RefundRequest {
    @Getter
    private String orderNo;
    private Long seatId;
    @Getter
    private Integer amount;
    private String reason;

    public RefundLog toLog(TossRefundResponse tossRefundResponse) {
        return new RefundLog(
                tossRefundResponse.getRefundNo(),
                tossRefundResponse.getPayToken(),
                tossRefundResponse.getRefundedAmount(),
                tossRefundResponse.getApprovalTime(),
                tossRefundResponse.getTransactionId(),
                this.reason
        );
    }
}
