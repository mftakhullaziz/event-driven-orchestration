package com.service.inventoryService.controller;

import com.service.domainPersistence.payload.inventory.InventoryRequest;
import com.service.domainPersistence.payload.inventory.InventoryResponse;
import com.service.domainPersistence.persistence.InventoryTRec;
import com.service.inventoryService.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("inventory")
@CrossOrigin(origins = "http://localhost:9093")
@RequiredArgsConstructor
public class InventoryRestController {

    private final InventoryService service;

    @PostMapping("/deductProductAmount")
    public Mono<InventoryResponse> deduct(@Valid @RequestBody InventoryRequest request){
        InventoryResponse response = new InventoryResponse();
        Mono<InventoryResponse> d = service.deductInventory(request).flatMap(
                data -> {
                    response.setOrderId(data.getProductId());
                    response.setStatus(data.getProductStatus());
                    response.setProductAmount(data.getProductAmount());
                    response.setProductPrice(data.getProductPricePerUnit());
                    return Mono.just(response);
                }
        );
        return d;
//        return service.deductInventory(request);
    }

    @PostMapping("/updateProductAmount")
    public Mono<InventoryTRec> add(@Valid @RequestBody InventoryRequest request){
        return service.addInventory(request);
    }

    @PostMapping("/createProductInventory")
    public Mono<InventoryTRec> createProductInventory(@Valid @RequestBody InventoryRequest request) {
        return service.createProductInventory(request);
    }

}
