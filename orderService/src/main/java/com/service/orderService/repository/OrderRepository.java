package com.service.orderService.repository;

import com.service.domainPersistence.persistence.OrderEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<OrderEntity, String> {
}
