package com.doodlemart.inventory.stock.dto;

public record RemoveStockRequest(
        Integer quantityToRemove
) {
}
