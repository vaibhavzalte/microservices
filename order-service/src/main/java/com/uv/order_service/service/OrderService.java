package com.uv.order_service.service;

import com.uv.order_service.entity.Inventory;
import com.uv.order_service.outbound.InventoryClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;
    private final InventoryClient inventoryClient;

    //    @RateLimiter(
//            name="inventoryService",fallbackMethod = "fallBackMethodInventory"
//    )
//    @Retryable(
//            retryFor = RuntimeException.class,
//            maxAttempts = 4,
//            backoff = @Backoff(delay = 2000)
//    )
    @CircuitBreaker(
            name = "inventoryServiceCkt", fallbackMethod = "fallBackeMethodCircuitBreaker"
    )
    public String buyProduct(String id) {


        ResponseEntity<String> response =
                inventoryClient.checkInventoryAvilability(id);

        if (response.getStatusCode().is2xxSuccessful()) {
            return "Product bought successfully";
        }

        return response.getBody();
    }

    public String fallBackMethodInventory(String id, Throwable throwable) {
        return "Rate Limiter is not allowing please try again";
    }

    public String fallBackeMethodCircuitBreaker(String id, Throwable throwable) {
        log.info("inventory service is down");
        return "Circuit breaker working.. inventory service is down";
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
