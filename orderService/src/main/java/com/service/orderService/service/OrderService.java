package com.service.orderService.service;

import com.service.domainPersistence.enumerate.OrderStatusEnum;
import com.service.domainPersistence.payload.orchestrator.OrchestratorRequest;
import com.service.domainPersistence.payload.order.OrderRequest;
import com.service.domainPersistence.persistence.OrderTRec;
import com.service.orderService.repository.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class OrderService implements OrderServiceGateway {

    private final OrderRepository repository;
    private final Sinks.Many<OrchestratorRequest> sinks;

    public OrderService(OrderRepository repository, Sinks.Many<OrchestratorRequest> sinks) {
        this.repository = repository;
        this.sinks = sinks;
    }

    @Override
    public Mono<OrderTRec> createOrder(OrderRequest request){
        return repository.save(builderOrder(request));
    }

    private OrderTRec builderOrder(OrderRequest request) {
        UUID uuidUser = UUID.randomUUID();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime dateTime = LocalDateTime.now();
        return OrderTRec.builder()
                .userId(uuidUser)
                .productPrice(request.getProductPrice())
                .productId(UUID.fromString(request.getProductId()))
                .productAmount(request.getProductAmount())
                .orderStatus(String.valueOf(OrderStatusEnum.ORDER_CREATED))
                .dateIndex(Integer.valueOf(formatter.format(dateTime)))
                .build();
    }

    @Override
    public Mono<OrderTRec> submitOrderProcess(OrderRequest request){
        return repository.findById(request.getOrderId())
                .flatMap(data -> {
                    data.setOrderStatus(String.valueOf(OrderStatusEnum.ORDER_PROCESSED));
                    return repository.save(data)
                            .doOnNext(e -> request.setProductId(String.valueOf(e.getProductId())))
                            .doOnNext(e -> request.setProductAmount(e.getProductAmount()))
                            .doOnNext(e -> request.setProductPrice(e.getProductPrice()))
                            .doOnNext(e -> emitEvent(request));
                });
    }

    private void emitEvent(OrderRequest request){
        this.sinks.tryEmitNext(getOrchestratorRequest(request));
    }

    public OrchestratorRequest getOrchestratorRequest(OrderRequest request){
        OrchestratorRequest orchestratorRequest = new OrchestratorRequest();
        orchestratorRequest.setProductId(request.getProductId());
        orchestratorRequest.setOrderId(request.getOrderId());
        orchestratorRequest.setProductPrice(request.getProductPrice());
        orchestratorRequest.setProductAmount(request.getProductAmount());
        return orchestratorRequest;
    }
}
