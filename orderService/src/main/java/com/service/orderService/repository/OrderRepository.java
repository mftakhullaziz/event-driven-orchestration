package com.service.orderService.repository;

import com.service.domainPersistence.persistence.OrderTRec;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<OrderTRec, String> {
}
