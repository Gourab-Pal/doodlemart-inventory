package com.doodlemart.inventory.stock.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "inventory", schema = "doodlemart_inventory")
public class Inventory {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "product_id", nullable = false, unique = true)
    private UUID productId;

    @Column(name = "total_quantity", nullable = false)
    private Integer totalQuantity;

    @Column(name = "reserved_quantity", nullable = false)
    private Integer reservedQuantity;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    protected Inventory() {}

    public Inventory(UUID productId) {
        this.id = UUID.randomUUID();
        this.productId = productId;
        this.totalQuantity = 0;
        this.reservedQuantity = 0;
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void updateTimestamp() {
        this.updatedAt = OffsetDateTime.now();
    }

    public UUID getId() {return id;}
    public UUID getProductId() {return productId;}
    public Integer getTotalQuantity() {return totalQuantity;}
    public Integer getReservedQuantity() {return reservedQuantity;}
    public OffsetDateTime getCreatedAt() {return createdAt;}
    public OffsetDateTime getUpdatedAt() {return updatedAt;}
}
