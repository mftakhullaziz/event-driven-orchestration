package com.service.domainPersistence.payload.orchestrator;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Data
public class OrchestratorResponse {
    private Integer userId;
    private UUID productId;
    private UUID orderId;
    private Double productPrice;
    private Double productAmount;
    private String orderStatus;
}
