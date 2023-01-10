package com.service.inventoryService.service;

import com.service.domainPersistence.payload.inventory.InventoryRequest;
import com.service.domainPersistence.persistence.InventoryEntity;
import reactor.core.publisher.Mono;

public interface InventoryServiceGateway {
    Mono<InventoryEntity> addInventory(InventoryRequest request);
    Mono<InventoryEntity> deductInventory(InventoryRequest request);
    Mono<InventoryEntity> createProductInventory(InventoryRequest request);
}
