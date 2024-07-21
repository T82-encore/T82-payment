package com.T82.payment.domain.response;

import com.T82.payment.domain.dto.TossRefundResponse;

public record RefundResponse(
        String message,
        String approvalTime,
        Integer refundedAmount
) {


    public static RefundResponse from(TossRefundResponse tossRefundResponse) {
        return new RefundResponse(
                "환불처리가 성공적으로 이루어졌습니다.",
                tossRefundResponse.getApprovalTime(),
                tossRefundResponse.getRefundedAmount()
        );
    }
}
