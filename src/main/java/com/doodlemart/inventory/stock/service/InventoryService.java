package com.doodlemart.inventory.stock.service;

import com.doodlemart.inventory.stock.dto.InventoryCreateRequest;
import com.doodlemart.inventory.stock.dto.InventoryResponse;
import com.doodlemart.inventory.stock.entity.Inventory;
import com.doodlemart.inventory.stock.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public InventoryResponse createInventory(InventoryCreateRequest request) {
        Inventory inventory = new Inventory(request.productId());
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryResponse.from(savedInventory);
    }

    public InventoryResponse getInventoryByProductId(UUID productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow();
        return InventoryResponse.from(inventory);
    }
}
