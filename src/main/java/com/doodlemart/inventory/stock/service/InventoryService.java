package com.doodlemart.inventory.stock.service;

import com.doodlemart.inventory.integration.product.ProductClient;
import com.doodlemart.inventory.stock.dto.*;
import com.doodlemart.inventory.stock.entity.Inventory;
import com.doodlemart.inventory.stock.exception.InventoryNotFoundException;
import com.doodlemart.inventory.stock.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductClient productClient;

    public InventoryService(InventoryRepository inventoryRepository, ProductClient productClient) {
        this.inventoryRepository = inventoryRepository;
        this.productClient = productClient;
    }

    public InventoryResponse createInventory(InventoryCreateRequest request) {
        productClient.verifyProductExists(request.productId());
        Inventory inventory = new Inventory(request.productId());
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryResponse.from(savedInventory);
    }

    public InventoryResponse getInventoryByProductId(UUID productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow(()->new InventoryNotFoundException(productId));
        return InventoryResponse.from(inventory);
    }

    @Transactional
    public InventoryResponse addStock(UUID productId, AddStockRequest request) {
        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow(()->new InventoryNotFoundException(productId));
        inventory.addStock(request.quantityToAdd());
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryResponse.from(savedInventory);
    }

    @Transactional
    public InventoryResponse removeStock(UUID productId, RemoveStockRequest request) {
        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow(()->new InventoryNotFoundException(productId));
        inventory.removeStock(request.quantityToRemove());
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryResponse.from(savedInventory);
    }

    @Transactional
    public InventoryResponse reserveStock(
            UUID productId,
            ReserveStockRequest request
    ) {
        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow(()->new InventoryNotFoundException(productId));
        inventory.reserveStock(request.quantityToReserve());
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryResponse.from(savedInventory);
    }

    @Transactional
    public InventoryResponse releaseStock(
            UUID productId,
            ReleaseStockRequest request
    ) {
        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow(()->new InventoryNotFoundException(productId));
        inventory.releaseStock(request.quantityToRelease());
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryResponse.from(savedInventory);
    }

    @Transactional
    public InventoryResponse confirmStock(
            UUID productId,
            ConfirmStockRequest request
    ) {
        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow(()->new InventoryNotFoundException(productId));
        inventory.confirmStock(request.quantityToConfirm());
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryResponse.from(savedInventory);
    }
}
