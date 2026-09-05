package com.doodlemart.inventory.integration.product;

import com.doodlemart.inventory.stock.exception.ProductNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Component
public class ProductClient {

    private final RestClient productRestClient;

    public ProductClient(RestClient productRestClient) {
        this.productRestClient = productRestClient;
    }

    public void verifyProductExists(UUID productId) {
        try {
            productRestClient
                    .get()
                    .uri("/api/v1/products/{productId}", productId)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.NotFound upstreamException) {
            throw new ProductNotFoundException(productId);
        }
    }
}
