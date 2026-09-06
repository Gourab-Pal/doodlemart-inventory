package com.doodlemart.inventory.integration.kafka.event;

import java.util.UUID;

public record ProductCreatedEvent(
        UUID productId
) {
}
