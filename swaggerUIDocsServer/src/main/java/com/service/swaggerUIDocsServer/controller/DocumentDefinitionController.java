package com.service.swaggerUIDocsServer.controller;

import com.service.swaggerUIDocsServer.configuration.ServiceDefinitionContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentDefinitionController {

    private final ServiceDefinitionContext definitionContext;

    public DocumentDefinitionController(ServiceDefinitionContext definitionContext) {
        this.definitionContext = definitionContext;
    }

    @GetMapping("/service/{serviceName}")
    public String getServiceDefinition(@PathVariable("serviceName") String serviceName){
        return definitionContext.getSwaggerDefinition(serviceName);
    }

}
