package com.uv.order_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final RestTemplate restTemplate;

    public String buyProduct(String id) {
        String response = restTemplate.getForObject(
                "http://localhost:8082/inventory/" + id,
                String.class
        );
        return response.equals("Available") ? "Product buy successfully" : response;
    }
}
