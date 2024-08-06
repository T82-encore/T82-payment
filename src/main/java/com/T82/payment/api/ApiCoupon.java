package com.T82.payment.api;

import com.T82.payment.config.jwt.TokenInfo;
import com.T82.payment.domain.request.CouponVerifyRequest;
import com.T82.payment.domain.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiCoupon {
    private final FeignCoupon feignCoupon;

    public void verifyCoupon(TokenInfo tokenInfo, PaymentRequest paymentRequest) {
        CouponVerifyRequest couponVerifyRequest = paymentRequest.convertToCouponVerifyRequest(tokenInfo);
        if(!couponVerifyRequest.getItems().isEmpty()) {
            try {
                feignCoupon.verify(couponVerifyRequest);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
