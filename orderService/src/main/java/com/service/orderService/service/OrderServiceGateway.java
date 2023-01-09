package com.service.orderService.service;

import com.service.domainPersistence.payload.order.OrderRequest;
import com.service.domainPersistence.persistence.OrderTRec;
import reactor.core.publisher.Mono;

public interface OrderServiceGateway {
    Mono<OrderTRec> createOrder(OrderRequest request);
    Mono<OrderTRec> submitOrderProcess(OrderRequest request);
}
