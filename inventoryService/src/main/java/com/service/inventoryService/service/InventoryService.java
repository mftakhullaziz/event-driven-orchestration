package com.service.inventoryService.service;

import com.service.domainPersistence.enumerate.InventoryStatusEnum;
import com.service.domainPersistence.payload.inventory.InventoryRequest;
import com.service.domainPersistence.persistence.InventoryEntity;
import com.service.inventoryService.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryService implements InventoryServiceGateway {

    private final InventoryRepository repository;

    @Override
    public Mono<InventoryEntity> createProductInventory(InventoryRequest request){
        return repository.save(builderOrder(request));
    }

    private InventoryEntity builderOrder(InventoryRequest request) {
        return InventoryEntity.builder()
                .productName(request.getProductName())
                .productPricePerUnit(request.getProductPrice())
                .productAmount(request.getProductAmount())
                .productStatus(String.valueOf(InventoryStatusEnum.AVAILABLE))
                .build();
    }

    @Override
    public Mono<InventoryEntity> addInventory(InventoryRequest request) {
        Double amount = request.getProductAmount();
        return repository.findById(UUID.fromString(request.getProductId()))
                .flatMap(data -> {
                            Double currentAmount = data.getProductAmount();
                            // add total new amount
                            Double updateAmount = amount + currentAmount;
                            data.setProductAmount(updateAmount);
                            return repository.save(data);
                });
    }

    @Override
    public Mono<InventoryEntity> deductInventory(InventoryRequest request) {
        return repository.findById(UUID.fromString(request.getProductId()))
                .flatMap(data -> {
                            Double amount = request.getProductAmount();
                            Double currentAmount = data.getProductAmount();
                            Double updateAmount = currentAmount - amount;
                            if (updateAmount < 0.0) updateAmount = 0.0;
                            data.setProductAmount(updateAmount);
                            if (updateAmount.equals(0.0)) {
                                data.setProductStatus(String.valueOf(InventoryStatusEnum.PRODUCT_EMPTY));
                            } else {
                                data.setProductStatus(String.valueOf(InventoryStatusEnum.AVAILABLE));
                            }
                            return repository.save(data);
                });
    }

}
