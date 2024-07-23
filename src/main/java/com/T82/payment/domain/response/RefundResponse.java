package com.T82.payment.domain.response;

import com.T82.payment.domain.dto.TossRefundDto;

public record RefundResponse(
        String message,
        String approvalTime,
        Integer refundedAmount
) {


    public static RefundResponse from(TossRefundDto tossRefundDto) {
        return new RefundResponse(
                "환불처리가 성공적으로 이루어졌습니다.",
                tossRefundDto.getApprovalTime(),
                tossRefundDto.getRefundedAmount()
        );
    }
}
