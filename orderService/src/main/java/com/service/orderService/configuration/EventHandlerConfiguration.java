package com.service.orderService.configuration;

import com.service.domainPersistence.payload.orchestrator.OrchestratorRequest;
import com.service.domainPersistence.payload.orchestrator.OrchestratorResponse;
import com.service.orderService.service.OrderEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Slf4j
@Configuration
public class EventHandlerConfiguration {

    private final Flux<OrchestratorRequest> flux;
    private final OrderEventService service;

    public EventHandlerConfiguration(Flux<OrchestratorRequest> flux, OrderEventService service) {
        this.flux = flux;
        this.service = service;
    }

    @Bean
    public Supplier<Flux<OrchestratorRequest>> supplier(){
        return () -> flux;
    };

    @Bean
    public Consumer<Flux<OrchestratorResponse>> consumer(){
        return f -> f
                .doOnNext(c -> System.out.println("\nCONSUMER MESSAGE :: " + c))
                .flatMap(service::orderEventProcess)
                .subscribe();
    };

}
