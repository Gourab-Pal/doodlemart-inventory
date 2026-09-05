package com.doodlemart.inventory.stock.dto;

import com.doodlemart.inventory.stock.entity.Inventory;

import java.time.OffsetDateTime;
import java.util.UUID;

public record InventoryResponse(
        UUID id,
        UUID productId,
        Integer totalQuantity,
        Integer reservedQuantity,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
    public static InventoryResponse from(Inventory inventory) {
        return new InventoryResponse(
                inventory.getId(),
                inventory.getProductId(),
                inventory.getTotalQuantity(),
                inventory.getReservedQuantity(),
                inventory.getCreatedAt(),
                inventory.getUpdatedAt()
        );
    }
}
