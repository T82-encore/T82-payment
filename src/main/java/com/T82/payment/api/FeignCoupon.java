package com.T82.payment.api;

import com.T82.payment.domain.request.CouponVerifyRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "coupon", url = "${feign.coupon.url}")
public interface FeignCoupon {
    @PostMapping("/verify")
    void verify(@RequestBody CouponVerifyRequest couponVerifyRequest);
}
