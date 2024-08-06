package com.T82.payment.service;

import com.T82.payment.config.kafka.KafkaUtil;
import com.T82.payment.config.util.TossUtil;
import com.T82.payment.domain.dto.TossRefundDto;
import com.T82.payment.domain.model.PaymentLog;
import com.T82.payment.domain.request.RefundRequest;
import com.T82.payment.domain.response.RefundResponse;
import com.T82.payment.repository.PaymentDAO;
import com.T82.payment.repository.RefundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {
    private final RefundRepository refundRepository;
    private final PaymentDAO paymentDAO;
    private final TossUtil tossUtil;
    private final KafkaProducerService kafkaProducerService;

    @Override
    public RefundResponse refund(RefundRequest refundRequest) {
        PaymentLog paymentLog = paymentDAO.findPaymentLogByOrderNo(refundRequest.getOrderNo());
        TossRefundDto tossRefundDto = tossUtil.refund(
                paymentLog.getPayToken(),
                refundRequest.getAmount()
        );
        if (tossRefundDto.getErrorCode() != null) {
            throw new IllegalArgumentException(tossRefundDto.getMsg());
        }

        refundRepository.save(refundRequest.toLog(tossRefundDto));

        Map<String, Object> refundMessage = KafkaUtil.getRefundMessage(refundRequest);
        kafkaProducerService.sendMessage("refundSeat", refundMessage);
        kafkaProducerService.sendMessage("refundTicket", refundMessage);

        return RefundResponse.from(tossRefundDto);
    }
}
