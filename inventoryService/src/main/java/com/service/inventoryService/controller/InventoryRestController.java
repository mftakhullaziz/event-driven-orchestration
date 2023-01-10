package com.service.inventoryService.controller;

import com.service.domainPersistence.payload.inventory.InventoryRequest;
import com.service.domainPersistence.payload.inventory.InventoryResponse;
import com.service.inventoryService.service.InventoryServiceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("inventory")
@CrossOrigin(origins = "http://localhost:9093")
@RequiredArgsConstructor
public class InventoryRestController {

    private final InventoryServiceGateway service;

    @PostMapping("/deductProductAmount")
    public Mono<InventoryResponse> deduct(@Valid @RequestBody InventoryRequest request){
        InventoryResponse response = new InventoryResponse();
        return service.deductInventory(request).flatMap(
                data -> {
                    response.setProductId(data.getProductId());
                    response.setStatus(data.getProductStatus());
                    response.setProductAmount(data.getProductAmount());
                    response.setProductPrice(data.getProductPricePerUnit());
                    return Mono.just(response);
                }
        ).switchIfEmpty(Mono.empty());
    }

    @PostMapping("/updateProductAmount")
    public Mono<InventoryResponse> add(@Valid @RequestBody InventoryRequest request){
        InventoryResponse response = new InventoryResponse();
        return service.addInventory(request).flatMap(
                res -> {
                    response.setProductId(res.getProductId());
                    response.setProductPrice(res.getProductPricePerUnit());
                    response.setProductAmount(res.getProductAmount());
                    response.setStatus(res.getProductStatus());
                    return Mono.just(response);
                }
        ).switchIfEmpty(Mono.empty());
    }

    @PostMapping("/createProductInventory")
    public Mono<InventoryResponse> createProductInventory(@Valid @RequestBody InventoryRequest request) {
        InventoryResponse response = new InventoryResponse();
        return service.createProductInventory(request).flatMap(
                res -> {
                    response.setProductId(res.getProductId());
                    response.setProductPrice(res.getProductPricePerUnit());
                    response.setProductAmount(res.getProductAmount());
                    response.setStatus(res.getProductStatus());
                    return Mono.just(response);
                }
        ).switchIfEmpty(Mono.empty());
    }

}
