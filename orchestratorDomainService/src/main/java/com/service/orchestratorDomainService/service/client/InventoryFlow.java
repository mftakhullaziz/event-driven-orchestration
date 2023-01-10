package com.service.orchestratorDomainService.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.domainPersistence.enumerate.InventoryStatusEnum;
import com.service.domainPersistence.payload.inventory.InventoryRequest;
import com.service.domainPersistence.payload.inventory.InventoryResponse;
import com.service.orchestratorDomainService.service.workflowGateway.WorkflowStep;
import com.service.orchestratorDomainService.service.workflowGateway.WorkflowStepStatus;
import lombok.SneakyThrows;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class InventoryFlow implements WorkflowStep {

    private final WebClient webClient;
    private final InventoryRequest request;
    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

    public InventoryFlow(WebClient webClient, InventoryRequest request) {
        this.webClient = webClient;
        this.request = request;
    }

    @Override
    public WorkflowStepStatus getStatus() {
        return this.stepStatus;
    }

    @SneakyThrows
    @Override
    public Mono<Boolean> process() {
        return  webClient
                .post()
                .uri("/inventory/deductProductAmount")
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(InventoryResponse.class)
                .map(m -> {
                    String status = m.getStatus();
                    String availableProduct = String.valueOf(InventoryStatusEnum.AVAILABLE);
                    return status.contains(availableProduct);
                }).doOnNext(b -> stepStatus = b ?
                        WorkflowStepStatus.COMPLETE : WorkflowStepStatus.FAILED);
    }

    @Override
    public Mono<Boolean> revert() {
        return this.webClient
                    .post()
                    .uri("/inventory/updateProductAmount")
                    .body(BodyInserters.fromValue(request))
                    .retrieve()
                    .bodyToMono(Void.class)
                    .map(r ->true)
                    .onErrorReturn(false);
    }
}
