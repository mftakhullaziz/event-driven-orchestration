package com.service.paymentService.service;

import com.service.domainPersistence.payload.payment.PaymentHistoricalRequest;
import com.service.domainPersistence.payload.payment.PaymentRequest;
import com.service.domainPersistence.persistence.PaymentEntity;
import com.service.domainPersistence.persistence.PaymentHistoricalEntity;
import reactor.core.publisher.Mono;

public interface PaymentServiceGateway {
    Mono<PaymentEntity> createCreditUser(PaymentRequest request);
    Mono<PaymentEntity> addCreditUser(PaymentRequest request);
    Mono<PaymentEntity> deductCreditUser(PaymentRequest request);
    Mono<PaymentHistoricalEntity> createUpdateCreditFromTransaction(PaymentHistoricalRequest request);
}
