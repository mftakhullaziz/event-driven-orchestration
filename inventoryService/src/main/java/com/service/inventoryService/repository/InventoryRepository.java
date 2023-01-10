package com.service.inventoryService.repository;

import com.service.domainPersistence.persistence.InventoryEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventoryRepository extends ReactiveCrudRepository<InventoryEntity, UUID> {
}
