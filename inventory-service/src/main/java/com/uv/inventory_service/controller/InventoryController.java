package com.uv.inventory_service.controller;

import com.uv.inventory_service.InventoryService;
import com.uv.inventory_service.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/{id}")
    public ResponseEntity<String> checkProductAvilability(@PathVariable String id) {
        boolean b = inventoryService.checkAbilablity(id);
        if (b) {
            return new ResponseEntity<>("Available", HttpStatus.OK);
        }
        return new ResponseEntity<>("NotAvailable", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        return new ResponseEntity<>(inventoryService.add(inventory), HttpStatus.CREATED);
    }
}
