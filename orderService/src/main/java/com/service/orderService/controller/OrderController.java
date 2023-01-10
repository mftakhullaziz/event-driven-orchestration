package com.service.orderService.controller;

import com.service.domainPersistence.payload.order.OrderRequest;
import com.service.domainPersistence.payload.order.OrderResponse;
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
    public Mono<OrderResponse> createOrder(@Valid @RequestBody OrderRequest requestMono){
        OrderResponse response = new OrderResponse();
        return service.createOrder(requestMono)
                .flatMap( res -> {
                    response.setOrderId(res.getOrderId());
                    response.setProductId(res.getProductId());
                    response.setUserId(res.getUserId());
                    response.setProductAmount(res.getProductAmount());
                    response.setProductPrice(res.getProductPrice());
                    response.setOrderStatus(res.getOrderStatus());
                    return Mono.just(response);
                }).switchIfEmpty(Mono.empty());
    }

    @PostMapping("/order/submitOrderProcess")
    public Mono<OrderResponse> submitOrderProcess(@Valid @RequestBody OrderRequest requestMono){
        OrderResponse response = new OrderResponse();
        return service.submitOrderProcess(requestMono)
                .flatMap( res -> {
                    response.setOrderId(res.getOrderId());
                    response.setProductId(res.getProductId());
                    response.setUserId(res.getUserId());
                    response.setProductAmount(res.getProductAmount());
                    response.setProductPrice(res.getProductPrice());
                    response.setOrderStatus(res.getOrderStatus());
                    return Mono.just(response);
                }).switchIfEmpty(Mono.empty());
    }
}
