package com.uv.inventory_service.controller;

import com.uv.inventory_service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> checkProductAvilability(@PathVariable String id){
        boolean b = inventoryService.checkAbilablity(id);
        if(b){
            return new ResponseEntity<>("Available",HttpStatus.OK);
        }
        return new ResponseEntity<>("NotAvailable",HttpStatus.NOT_FOUND);
    }
}
