package com.service.domainPersistence.payload.payment;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Data
public class PaymentResponse {
    private Integer userId;
    private Integer productId;
    private UUID orderId;
    private Double productAmount;
    private Double productPrice;
    private String status;
}
