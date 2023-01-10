package com.service.inventoryService.service;

import com.service.domainPersistence.payload.inventory.InventoryRequest;
import com.service.domainPersistence.persistence.InventoryTRec;
import reactor.core.publisher.Mono;

public interface InventoryServiceGateway {
    Mono<InventoryTRec> addInventory(InventoryRequest request);
    Mono<InventoryTRec> deductInventory(InventoryRequest request);
    Mono<InventoryTRec> createProductInventory(InventoryRequest request);
}
