package com.uv.order_service.service;

import com.uv.order_service.entity.Inventory;
import com.uv.order_service.outbound.InventoryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;
    private final InventoryClient inventoryClient;

    @Retryable(
            retryFor = RuntimeException.class,
            maxAttempts = 4,
            backoff = @Backoff(delay = 2000)
    )
    public String buyProduct(String id) {

        System.out.println("Calling inventory service...");

        ResponseEntity<String> response =
                inventoryClient.checkInventoryAvilability(id);

        if (response.getStatusCode().is2xxSuccessful()) {
            return "Product bought successfully";
        }

        return response.getBody();
    }

    public Inventory addInventory(Inventory inventory) {
        return inventoryClient.addInventory(inventory);
    }

    public ResponseEntity<Inventory> addInventory2(Inventory inventory) {
        return restClient.post()
                .uri("http://localhost:8082/inventory/add")
                .body(inventory)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                    throw new RuntimeException(response.getBody().toString());
                }))
                .toEntity(Inventory.class);
    }

    public ResponseEntity<Inventory> addInventory3(Inventory inventory) {

        return restClient.post()
                .uri("http://localhost:8082/inventory/add")
                .body(inventory)
                .exchange((request, response) -> {

                    if (response.getStatusCode().is4xxClientError()) {
                        String errorMessage =
                                new String(response.getBody().readAllBytes());

                        throw new RuntimeException(errorMessage);
                    }

                    Inventory body =
                            objectMapper.readValue(response.getBody(), Inventory.class);

                    return ResponseEntity
                            .status(response.getStatusCode())
                            .body(body);
                });
    }
}
