package com.uv.inventory_service;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    public boolean checkAbilablity(String id) {
        return id.equals("1");
    }
}
