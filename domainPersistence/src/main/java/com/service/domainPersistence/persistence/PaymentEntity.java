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
@Table(name = "payments_table")
public class PaymentEntity {

    @Id
    @Column(value = "payment_id")
    private UUID paymentId;

    @Column(value = "user_id")
    private UUID userId;

    @Column(value = "credit_amount")
    private Double creditAmount;

    @Column(value = "credit_status")
    private String creditStatus;

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
