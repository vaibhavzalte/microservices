package com.uv.order_service.outbound;

import com.uv.order_service.config.InventoryFeignConfig;
import com.uv.order_service.entity.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service", url = "http://localhost:8082", configuration = InventoryFeignConfig.class)
public interface InventoryClient {

    @GetMapping("/inventory/{productId}")
    ResponseEntity<String> checkInventoryAvilability(@PathVariable String productId);

    @PostMapping("/inventory/add")
    Inventory addInventory(@RequestBody Inventory inventory);
}
