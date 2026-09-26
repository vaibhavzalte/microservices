package com.uv.order_service.controller;

import com.uv.order_service.entity.Inventory;
import com.uv.order_service.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{id}")
    public String buyProduct(@PathVariable String id) {
        return orderService.buyProduct(id);
    }

    @PostMapping("/add")
    public Inventory addInventory(@RequestBody Inventory inventory){
        return orderService.addInventory(inventory);
    }
}
