package com.doodlemart.inventory.integration.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ProductClientConfig {

    @Bean
    public RestClient productRestClient(
            @Value("${doodlemart.product-service.base-url}")
            String productServiceBaseUrl
    ) {
        return RestClient.builder().baseUrl(productServiceBaseUrl).build();
    }
}
