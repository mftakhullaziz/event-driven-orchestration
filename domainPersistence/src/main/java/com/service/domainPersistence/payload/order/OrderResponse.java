package com.service.domainPersistence.payload.order;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class OrderResponse {
    private UUID orderId;
    private UUID productId;
    private UUID userId;
    private Double productPrice;
    private Double productAmount;
    private String orderStatus;
}
