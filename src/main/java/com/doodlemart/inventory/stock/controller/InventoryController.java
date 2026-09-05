package com.doodlemart.inventory.stock.controller;

import com.doodlemart.inventory.stock.dto.AddStockRequest;
import com.doodlemart.inventory.stock.dto.InventoryCreateRequest;
import com.doodlemart.inventory.stock.dto.InventoryResponse;
import com.doodlemart.inventory.stock.dto.RemoveStockRequest;
import com.doodlemart.inventory.stock.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryResponse getInventoryByProductId(
            @PathVariable
            UUID productId
    ) {
        return inventoryService.getInventoryByProductId(productId);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponse createInventory(@RequestBody InventoryCreateRequest request) {
        return inventoryService.createInventory(request);
    }

    @PatchMapping("/products/{productId}/add-stock")
    @ResponseStatus(HttpStatus.OK)
    public InventoryResponse addStock(
            @RequestBody
            AddStockRequest request,

            @PathVariable
            UUID productId
    ) {
        return inventoryService.addStock(productId, request);
    }

    @PatchMapping("/products/{productId}/remove-stock")
    @ResponseStatus(HttpStatus.OK)
    public InventoryResponse removeStock(
            @RequestBody
            RemoveStockRequest request,

            @PathVariable
            UUID productId
    ) {
        return inventoryService.removeStock(productId, request);
    }
}
