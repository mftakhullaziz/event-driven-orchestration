//package com.service.orchestratorDomainService.service.client;
//
//import com.service.domainPersistence.payload.payment.PaymentRequest;
//import com.service.domainPersistence.payload.payment.PaymentResponse;
//import com.service.orchestratorDomainService.service.workflowGateway.WorkflowStep;
//import com.service.orchestratorDomainService.service.workflowGateway.WorkflowStepStatus;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//public class PaymentStep implements WorkflowStep {
//
//    private final WebClient webClient;
//    private final PaymentRequest request;
//    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;
//
//    public PaymentStep(WebClient webClient, PaymentRequest request) {
//        this.webClient = webClient;
//        this.request = request;
//    }
//
//    @Override
//    public WorkflowStepStatus getStatus() {
//        return this.stepStatus;
//    }
//
//    @Override
//    public Mono<Boolean> process() {
//        return this.webClient
//                    .post()
//                    .uri("/payment/debit")
//                    .body(BodyInserters.fromValue(request))
//                    .retrieve()
//                    .bodyToMono(PaymentResponse.class)
//                    .map(r -> false)
//                    .doOnNext(b -> this.stepStatus = b ?
//                            WorkflowStepStatus.COMPLETE : WorkflowStepStatus.FAILED);
//    }
//
//    @Override
//    public Mono<Boolean> revert() {
//        return this.webClient
//                .post()
//                .uri("/payment/credit")
//                .body(BodyInserters.fromValue(request))
//                .retrieve()
//                .bodyToMono(Void.class)
//                .map(r -> true)
//                .onErrorReturn(false);
//    }
//
//}
