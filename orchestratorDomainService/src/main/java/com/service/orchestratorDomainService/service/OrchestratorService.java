package com.service.orchestratorDomainService.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.domainPersistence.enumerate.OrderStatusEnum;
import com.service.domainPersistence.payload.inventory.InventoryRequest;
import com.service.domainPersistence.payload.orchestrator.OrchestratorRequest;
import com.service.domainPersistence.payload.orchestrator.OrchestratorResponse;
import com.service.orchestratorDomainService.service.client.InventoryFlow;
import com.service.orchestratorDomainService.service.workflowGateway.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class OrchestratorService {

    private final WebClient inventoryClient;

    public OrchestratorService(@Qualifier("inventory") WebClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    @SneakyThrows
    public Mono<OrchestratorResponse> orderProduct(OrchestratorRequest requestDTO){
        Workflow orderWorkflow = getOrderWorkflow(requestDTO);
        return Flux.fromStream(() -> orderWorkflow.getSteps().stream())
                .flatMap(WorkflowStep::process)
                .handle(((aBoolean, synchronousSink) -> {
                    if (aBoolean) {
                        synchronousSink.next(true);
                    } else {
                        synchronousSink.error(new WorkflowException("Processed order failed!"));
                    }
                }))
                .then(Mono.fromCallable(() -> getResponseDTO(requestDTO, OrderStatusEnum.ORDER_COMPLETED)))
                .onErrorResume(ex -> revertOrder(orderWorkflow, requestDTO));
    }

    private Mono<OrchestratorResponse> revertOrder(Workflow workflow, OrchestratorRequest requestDTO){
        return Flux.fromStream(() -> workflow.getSteps().stream())
                .filter(wf -> wf.getStatus().equals(WorkflowStepStatus.COMPLETE))
                .flatMap(WorkflowStep::revert)
                .retry(5)
                .then(Mono.just(getResponseDTO(requestDTO, OrderStatusEnum.ORDER_CANCELLED)));
    }

    @SneakyThrows
    private Workflow getOrderWorkflow(OrchestratorRequest requestDTO){
//        WorkflowStep paymentStep = new PaymentStep(paymentClient, getPaymentRequestDTO(requestDTO));
        WorkflowStep inventoryStep = new InventoryFlow(inventoryClient, getInventoryRequestDTO(requestDTO));
        return new OrderWorkflow(List.of(inventoryStep));
    }

    private OrchestratorResponse getResponseDTO(OrchestratorRequest requestDTO, OrderStatusEnum status){
        OrchestratorResponse responseDTO = new OrchestratorResponse();
        responseDTO.setOrderId(UUID.fromString(requestDTO.getOrderId()));
        responseDTO.setProductAmount(requestDTO.getProductAmount());
        responseDTO.setProductId(UUID.fromString(requestDTO.getProductId()));
        responseDTO.setUserId(requestDTO.getUserId());
        responseDTO.setProductPrice(requestDTO.getProductPrice());
        responseDTO.setOrderStatus(String.valueOf(status));
        return responseDTO;
    }

//    private PaymentRequestDTO getPaymentRequestDTO(OrchestratorRequestDTO requestDTO){
//        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
//        paymentRequestDTO.setUserId(requestDTO.getUserId());
//        paymentRequestDTO.setAmount(requestDTO.getAmount());
//        paymentRequestDTO.setOrderId(requestDTO.getOrderId());
//        return paymentRequestDTO;
//    }

    private InventoryRequest getInventoryRequestDTO(OrchestratorRequest requestDTO){
        InventoryRequest inventoryRequestDTO = new InventoryRequest();
        inventoryRequestDTO.setProductId(String.valueOf(requestDTO.getProductId()));
        inventoryRequestDTO.setProductAmount(requestDTO.getProductAmount());
        return inventoryRequestDTO;
    }


}
