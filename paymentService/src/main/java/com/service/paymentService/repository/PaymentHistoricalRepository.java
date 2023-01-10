package com.service.paymentService.repository;

import com.service.domainPersistence.persistence.PaymentHistoricalEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentHistoricalRepository extends ReactiveCrudRepository<PaymentHistoricalEntity, UUID> {
}
