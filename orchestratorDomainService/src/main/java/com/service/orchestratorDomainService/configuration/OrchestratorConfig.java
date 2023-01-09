package com.service.orchestratorDomainService.configuration;

import com.service.domainPersistence.payload.orchestrator.OrchestratorRequest;
import com.service.domainPersistence.payload.orchestrator.OrchestratorResponse;
import com.service.orchestratorDomainService.service.OrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Configuration
public class OrchestratorConfig {

    private final OrchestratorService orchestratorService;

    public OrchestratorConfig(OrchestratorService orchestratorService) {
        this.orchestratorService = orchestratorService;
    }

    @Bean
    public Function<Flux<OrchestratorRequest>, Flux<OrchestratorResponse>> processor(){
        return flux -> flux
                .flatMap(orchestratorService::orderProduct)
                .doOnNext(request -> System.out.println("Status : " + request.getOrderStatus()));
    }

}
