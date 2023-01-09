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
@Table(name = "inventory_products")
public class InventoryTRec {

    @Id
    @Column(value = "product_id")
    private UUID productId;

    @Column(value = "product_name")
    private String productName;

    @Column(value = "product_price_per_unit")
    private Double productPricePerUnit;

    @Column(value = "product_amount")
    private Double productAmount;

    @Column(value = "product_status")
    private String productStatus;

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
