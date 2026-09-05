package com.doodlemart.inventory.stock.service;

import com.doodlemart.inventory.stock.dto.AddStockRequest;
import com.doodlemart.inventory.stock.dto.InventoryCreateRequest;
import com.doodlemart.inventory.stock.dto.InventoryResponse;
import com.doodlemart.inventory.stock.dto.RemoveStockRequest;
import com.doodlemart.inventory.stock.entity.Inventory;
import com.doodlemart.inventory.stock.repository.InventoryRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public InventoryResponse addStock(UUID productId, AddStockRequest request) {
        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow();
        inventory.addStock(request.quantityToAdd());
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryResponse.from(savedInventory);
    }

    @Transactional
    public InventoryResponse removeStock(UUID productId, RemoveStockRequest request) {
        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow();
        inventory.removeStock(request.quantityToRemove());
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryResponse.from(savedInventory);
    }
}
