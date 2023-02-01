package com.service.domainPersistence.payload.payment;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Data
public class PaymentRequest {
    private UUID paymentId;
    private UUID userId;
    private String productId;
    private String orderId;
    private String creditName;
    private Integer creditNumber;
    private Double creditAmount;
}
