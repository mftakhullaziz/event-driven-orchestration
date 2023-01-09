package com.service.orderService.controller;

import com.service.domainPersistence.payload.order.OrderRequest;
import com.service.domainPersistence.persistence.OrderTRec;
import com.service.orderService.service.OrderService;
import com.service.orderService.service.OrderServiceGateway;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:9093")
public class OrderController {

    private final OrderServiceGateway service;

    public OrderController(OrderServiceGateway service) {
        this.service = service;
    }

    @PostMapping("/order/create")
    public Mono<OrderTRec> createOrder(@Valid @RequestBody Mono<OrderRequest> requestMono){
        return requestMono.flatMap(service::createOrder);
    }

    @PostMapping("/order/submitOrderProcess")
    public Mono<OrderTRec> submitOrderProcess(@Valid @RequestBody Mono<OrderRequest> requestMono){
        return requestMono.flatMap(service::submitOrderProcess);
    }
}
