package com.service.orchestratorDomainService.service.workflowGateway;

import reactor.core.publisher.Mono;

public interface WorkflowStep {

    WorkflowStepStatus getStatus();
    Mono<Boolean> process();
    Mono<Boolean> revert();

}
