package com.service.domainPersistence.payload.orchestrator;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Data
public class OrchestratorRequest {
    private Integer userId;
    private String productId;
    private String orderId;
    private Double productPrice;
    private Double productAmount;
}
