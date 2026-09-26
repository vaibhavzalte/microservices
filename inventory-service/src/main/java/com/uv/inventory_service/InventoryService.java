package com.uv.inventory_service;

import com.uv.inventory_service.entity.Inventory;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    public boolean checkAbilablity(String id) {
        return id.equals("1");
    }

    public Inventory add(Inventory inventory) {
        System.out.println("Product added");
        return inventory;
    }
}
