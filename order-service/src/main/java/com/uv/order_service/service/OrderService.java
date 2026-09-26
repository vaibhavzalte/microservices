package com.uv.order_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final RestClient restClient;

    public String buyProduct(String id) {
        String response = restClient.get()
                .uri("http://localhost:8082/inventory/{id}", id)
                .retrieve()
                .body(String.class);
        return response.equals("Available") ? "Product buy successfully" : response;
    }
}
