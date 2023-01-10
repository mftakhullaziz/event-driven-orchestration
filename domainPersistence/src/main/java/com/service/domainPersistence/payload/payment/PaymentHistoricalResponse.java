package com.service.domainPersistence.payload.payment;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Data
public class PaymentHistoricalResponse {

    private UUID historyId;
    private UUID paymentId;
    private UUID userId;
    private UUID productId;
    private UUID orderId;
    private Double totalPaymentAmount;
    private String paymentStatus;

}
