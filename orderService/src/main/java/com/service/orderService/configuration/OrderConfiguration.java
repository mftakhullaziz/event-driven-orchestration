package com.service.orderService.configuration;

import com.service.domainPersistence.payload.orchestrator.OrchestratorRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
public class OrderConfiguration {
    @Bean
    public Sinks.Many<OrchestratorRequest> sinks(){
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Flux<OrchestratorRequest> flux(Sinks.Many<OrchestratorRequest> sinks){
        return sinks.asFlux();
    }
}
