package com.T82.payment.domain.request;

import com.T82.payment.domain.dto.TossRefundDto;
import com.T82.payment.domain.model.RefundLog;
import lombok.Getter;

public class RefundRequest {
    @Getter
    private String orderNo;
    @Getter
    private Long seatId;
    @Getter
    private Integer amount;
    private String reason;

    public RefundLog toLog(TossRefundDto tossRefundDto) {
        return new RefundLog(
                tossRefundDto.getRefundNo(),
                tossRefundDto.getPayToken(),
                tossRefundDto.getRefundedAmount(),
                tossRefundDto.getApprovalTime(),
                tossRefundDto.getTransactionId(),
                this.reason
        );
    }
}
