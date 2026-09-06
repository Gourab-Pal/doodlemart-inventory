package com.doodlemart.inventory.integration.kafka;

import com.doodlemart.inventory.integration.kafka.event.ProductCreatedEvent;
import com.doodlemart.inventory.stock.service.InventoryService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductCreatedEventConsumer {

    private final InventoryService inventoryService;

    public ProductCreatedEventConsumer(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @KafkaListener(
            topics = "${doodlemart.kafka.topic.product-created}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(ProductCreatedEvent event) {
        inventoryService.createInventoryFromProductCreatedEvent(event.productId());
    }
}
