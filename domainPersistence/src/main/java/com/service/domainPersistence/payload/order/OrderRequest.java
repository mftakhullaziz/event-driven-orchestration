package com.service.domainPersistence.payload.order;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequest {
    private Integer userId;
    private String productId;
    private String orderId;
    private Double productPrice;
    private Double productAmount;
}
