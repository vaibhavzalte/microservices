package com.uv.inventory_service.controller;

import com.uv.inventory_service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/{id}")
    public String checkProductAvilability(@PathVariable String id){
        return inventoryService.checkAbilablity(id);
    }
}
