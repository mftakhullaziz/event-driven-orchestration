package com.service.domainPersistence.payload.order;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class OrderResponse {
    private Integer userId;
    private UUID productId;
    private UUID orderId;
    private Double productPrice;
    private Double productAmount;
    private String orderStatus;
}
