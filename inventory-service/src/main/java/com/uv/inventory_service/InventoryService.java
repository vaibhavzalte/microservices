package com.uv.inventory_service;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    public String checkAbilablity(String id) {
       return id.equals("1") ? "Available":"Product not found";
    }
}
