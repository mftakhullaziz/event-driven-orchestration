package com.service.domainPersistence.persistence;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments_historical_table")
public class PaymentHistoricalEntity {

    @Id
    @Column(value = "payment_history_id")
    private UUID paymentHistoricalId;

    @Column(value = "payment_id")
    private UUID paymentId;

    @Column(value = "user_id")
    private UUID userId;

    @Column(value = "product_id")
    private UUID productId;

    @Column(value = "order_id")
    private UUID orderId;

    @Column(value = "credit_balance")
    private Double creditBalance;

    @Column(value = "remaining_balance")
    private Double remainingBalance;

    @Column(value = "payment_amount")
    private Double paymentAmount;

    @Column(value = "payment_status")
    private String paymentStatus;

    @Column(value = "date_index")
    private Integer dateIndex;

    @Column(value = "creation_at")
    private LocalDateTime creationAt;

    @Column(value = "creation_by")
    private String creationBy;

    @Column(value = "last_updated_at")
    private LocalDateTime lastUpdateAt;

    @Column(value = "last_updated_by")
    private String lastUpdatedBy;

}
