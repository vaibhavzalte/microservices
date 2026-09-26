package com.uv.order_service.service;

import com.uv.order_service.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final RestClient restClient;

    public String buyProduct(String id) {
        try {
            ResponseEntity<String> response = restClient.get()
                    .uri("http://localhost:8082/inventory/{id}", id)
                    .retrieve()
                    .toEntity(String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return "Product bought successfully";
            }

            return response.getBody();

        } catch (HttpClientErrorException.NotFound e) {
            return e.getResponseBodyAsString();

        }
    }

    public Inventory addInventory(Inventory inventory) {
        return restClient.post()
                .uri("http://localhost:8082/inventory/add")
                .body(inventory)
                .retrieve()
                .body(Inventory.class);
    }
}
