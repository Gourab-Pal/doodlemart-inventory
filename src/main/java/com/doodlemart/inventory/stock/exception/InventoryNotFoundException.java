package com.doodlemart.inventory.stock.exception;

import java.util.UUID;

public class InventoryNotFoundException extends RuntimeException {

    public InventoryNotFoundException(UUID productId) {
        super("No inventory found for product id: " + productId);
    }
}
