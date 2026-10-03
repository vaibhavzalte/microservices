package com.uv.order_service.controller;

import com.uv.order_service.entity.Inventory;
import com.uv.order_service.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{id}")
    public String buyProduct(@PathVariable String id,@RequestHeader(value = "X-test-color",required = false) String header) {
        System.out.println("X-test-color: " + header);
        return orderService.buyProduct(id);
    }

    @PostMapping("/add")
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return orderService.addInventory(inventory);
    }

    @PostMapping("/add2")
    public ResponseEntity<Inventory> addInventory2(@RequestBody Inventory inventory) {
        return orderService.addInventory2(inventory);
    }

    @PostMapping("/add3")
    public ResponseEntity<Inventory> addInventory3(@RequestBody Inventory inventory) {
        return orderService.addInventory3(inventory);
    }
}
