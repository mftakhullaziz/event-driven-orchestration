package com.service.orderService.service;

import com.service.domainPersistence.payload.order.OrderRequest;
import com.service.domainPersistence.persistence.OrderEntity;
import reactor.core.publisher.Mono;

public interface OrderServiceGateway {
    Mono<OrderEntity> createOrder(OrderRequest request);
    Mono<OrderEntity> submitOrderProcess(OrderRequest request);
}
