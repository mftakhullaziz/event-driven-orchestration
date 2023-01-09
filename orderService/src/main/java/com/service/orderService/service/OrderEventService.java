package com.service.orderService.service;

import com.service.domainPersistence.payload.orchestrator.OrchestratorResponse;
import com.service.orderService.repository.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderEventService {

    private final OrderRepository repository;

    public OrderEventService(OrderRepository repository) {
        this.repository = repository;
    }

    public Mono<Void> orderEventProcess(OrchestratorResponse response){
        return repository.findById(String.valueOf(response.getOrderId()))
                .doOnNext(p -> p.setOrderStatus(response.getOrderStatus()))
                .flatMap(repository::save)
                .then();
    }

}
