package com.doodlemart.inventory.integration.kafka;

import com.doodlemart.inventory.integration.kafka.event.ProductCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductCreatedDltConsumer {

    private static final Logger log = LoggerFactory.getLogger(ProductCreatedDltConsumer.class);

    @KafkaListener(
            topics = "${doodlemart.kafka.topic.product-created-dlt}",
            groupId = "${spring.kafka.consumer.group-id}-dlt"
    )
    public void consume(ProductCreatedEvent event) {
        log.error("ProductCreatedEvent event moved to DLT. Product ID: {}", event.productId());
    }
}
