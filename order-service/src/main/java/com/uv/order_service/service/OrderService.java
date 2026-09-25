package com.uv.order_service.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public String buyProduct(String id) {
        //TODO call inventory and check is it Available
        return "Product buy successfully";
    }
}
