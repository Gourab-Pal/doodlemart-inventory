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

    public void addStock(Integer quantityToAdd) {
        if (quantityToAdd == null || quantityToAdd<=0) {
            throw new IllegalArgumentException("You can add non-zero quantity only");
        }
        this.totalQuantity = this.totalQuantity + quantityToAdd;
        this.updatedAt = OffsetDateTime.now();
    }

    public void removeStock(Integer quantityToRemove) {
        if (quantityToRemove == null || quantityToRemove<=0) {
            throw new IllegalArgumentException("You can remove non-zero quantity only");
        }

        int availableQuantity = this.totalQuantity - this.reservedQuantity;

        if(quantityToRemove>availableQuantity) {
            throw new IllegalStateException("You don't have sufficient quantity to remove given amount of stock");
        }

        this.totalQuantity = this.totalQuantity - quantityToRemove;
        this.updatedAt = OffsetDateTime.now();
    }

    public void reserveStock(Integer quantityToReserve) {
        if (quantityToReserve == null || quantityToReserve<=0) {
            throw new IllegalArgumentException("You can reserve non-zero quantity only");
        }

        int availableQuantity = this.totalQuantity - this.reservedQuantity;

        if(quantityToReserve>availableQuantity) {
            throw new IllegalStateException("Can not reserve quantity more than what is available");
        }

        this.reservedQuantity = this.reservedQuantity + quantityToReserve;
        this.updatedAt = OffsetDateTime.now();
    }

    public void releaseStock(Integer quantityToRelease) {
        if (quantityToRelease == null || quantityToRelease<=0) {
            throw new IllegalArgumentException("You can release non-zero quantity only");
        }

        if(quantityToRelease>this.reservedQuantity) {
            throw new IllegalStateException("Can not release quantity more than what is reserved");
        }

        this.reservedQuantity = this.reservedQuantity - quantityToRelease;
        this.updatedAt = OffsetDateTime.now();
    }

    public void confirmStock(Integer quantityToConfirm) {
        if (quantityToConfirm == null || quantityToConfirm<=0) {
            throw new IllegalArgumentException("You can confirm non-zero quantity only");
        }

        if(quantityToConfirm>this.reservedQuantity) {
            throw new IllegalStateException("Can not confirm quantity more than what is reserved");
        }

        this.reservedQuantity = this.reservedQuantity - quantityToConfirm;
        this.totalQuantity = this.totalQuantity - quantityToConfirm;
        this.updatedAt = OffsetDateTime.now();
    }
}
