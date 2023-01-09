package com.service.domainPersistence.payload.inventory;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Data
public class InventoryRequest {
    private Integer userId;
    private String productId;
    private String productName;
    private UUID orderId;
    private Double productAmount;
    private Double productPrice;
}
