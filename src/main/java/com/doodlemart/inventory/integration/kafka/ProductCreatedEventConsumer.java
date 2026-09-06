package com.doodlemart.inventory.integration.kafka;

import com.doodlemart.inventory.integration.kafka.event.ProductCreatedEvent;
import com.doodlemart.inventory.stock.service.InventoryService;
import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Component;

@Component
public class ProductCreatedEventConsumer {

    private final InventoryService inventoryService;

    public ProductCreatedEventConsumer(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @RetryableTopic(
            attempts = "4",
            backOff = @BackOff(
                    delay = 1000,
                    multiplier = 2.0,
                    maxDelay = 5000
            )
    )

    @KafkaListener(
            topics = "${doodlemart.kafka.topic.product-created}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(ProductCreatedEvent event) {
        inventoryService.createInventoryFromProductCreatedEvent(event.productId());
    }
}
