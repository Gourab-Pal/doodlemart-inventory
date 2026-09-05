package com.doodlemart.inventory.stock.dto;

import java.util.UUID;

public record InventoryCreateRequest(
        UUID productId
) {

}
