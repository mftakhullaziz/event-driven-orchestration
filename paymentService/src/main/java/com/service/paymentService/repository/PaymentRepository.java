package com.service.paymentService.repository;

import com.service.domainPersistence.persistence.PaymentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepository extends ReactiveCrudRepository<PaymentEntity, UUID> {
}
