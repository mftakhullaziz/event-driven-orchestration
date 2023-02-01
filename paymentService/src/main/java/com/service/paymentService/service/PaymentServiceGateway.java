package com.service.paymentService.service;

import com.service.domainPersistence.payload.payment.PaymentRequest;
import com.service.domainPersistence.persistence.PaymentEntity;
import com.service.domainPersistence.persistence.PaymentHistoricalEntity;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public interface PaymentServiceGateway {
    Mono<PaymentEntity> createCreditUser(PaymentRequest request);
    Mono<PaymentEntity> addCreditUser(PaymentRequest request);
    Mono<Tuple2<PaymentEntity, PaymentHistoricalEntity>> deductCreditUser(PaymentRequest request);
}
