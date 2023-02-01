package com.service.paymentService.service;

import com.service.domainPersistence.payload.payment.PaymentRequest;
import com.service.domainPersistence.persistence.PaymentEntity;
import com.service.domainPersistence.persistence.PaymentHistoricalEntity;
import com.service.paymentService.repository.PaymentHistoricalRepository;
import com.service.paymentService.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static com.service.domainPersistence.enumerate.PaymentStatusEnum.CREDIT_ACTIVATE;
import static com.service.domainPersistence.enumerate.PaymentStatusEnum.CREDIT_DEACTIVATE;

@Service
public class PaymentService implements PaymentServiceGateway{

    private final PaymentRepository paymentRepository;
    private final PaymentHistoricalRepository paymentHistoricalRepository;

    public PaymentService(PaymentRepository paymentRepository, PaymentHistoricalRepository paymentHistoricalRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentHistoricalRepository = paymentHistoricalRepository;
    }

    @Override
    public Mono<PaymentEntity> createCreditUser(PaymentRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime dateTime = LocalDateTime.now();
        return Mono.from(paymentRepository.save(
                PaymentEntity.builder()
                        .userId(request.getUserId())
                        .creditName(request.getCreditName())
                        .creditNumber(request.getCreditNumber())
                        .creditAmount(request.getCreditAmount())
                        .dateIndex(Integer.valueOf(formatter.format(dateTime)))
                        .creditStatus(String.valueOf(CREDIT_ACTIVATE))
                        .build()
        ).switchIfEmpty(Mono.empty()));
    }

    @Override
    public Mono<PaymentEntity> addCreditUser(PaymentRequest request) {
        return Mono.from(paymentRepository.findById(request.getPaymentId())
                .flatMap(data -> {
                    Double currentAmount = data.getCreditAmount();
                    double updatedAmount = currentAmount + request.getCreditAmount();
                    if (updatedAmount < 0) data.setCreditStatus(String.valueOf(
                            CREDIT_DEACTIVATE));
                    else data.setCreditStatus(String.valueOf(CREDIT_ACTIVATE));
                    data.setCreditAmount(updatedAmount);
                    return paymentRepository.save(data);
                }));
    }

    @Override
    public Mono<Tuple2<PaymentEntity, PaymentHistoricalEntity>> deductCreditUser(PaymentRequest request) {
        return Mono.from(paymentRepository.findById(request.getPaymentId())
                .flatMap(
                        data -> {
                            Double currentAmount = data.getCreditAmount();
                            double updatedAmount = currentAmount - request.getCreditAmount();
                            if (updatedAmount < 0) data.setCreditStatus(String.valueOf(CREDIT_DEACTIVATE));
                            else data.setCreditStatus(String.valueOf(CREDIT_ACTIVATE));
                            data.setCreditAmount(updatedAmount);

                            return paymentRepository.save(data)
                                    .zipWith(constructDTOHistoricalPayment(request, currentAmount, updatedAmount));
                        })
        ).switchIfEmpty(Mono.empty());
    }


    private Mono<PaymentHistoricalEntity> constructDTOHistoricalPayment(PaymentRequest request,
                                                                        Double currentBalance, Double updateBalance) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime dateTime = LocalDateTime.now();
        String status;
        if (currentBalance - request.getCreditAmount() <= 0) status = "PAYMENT_FAILED";
        else status = "PAYMENT_SUCCESS";
        PaymentHistoricalEntity data = PaymentHistoricalEntity.builder()
                .paymentId(request.getPaymentId())
                .userId(request.getUserId())
                .productId(UUID.fromString(request.getProductId()))
                .orderId(UUID.fromString(request.getOrderId()))
                .creditBalance(currentBalance)
                .remainingBalance(updateBalance)
                .paymentAmount(request.getCreditAmount())
                .paymentStatus(status)
                .dateIndex(Integer.valueOf(formatter.format(dateTime)))
                .build();
        return paymentHistoricalRepository.save(data);
    }

}
