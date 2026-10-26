package com.uv.inventory_service.listener;

import com.uv.inventory_service.entity.Inventory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedListener {

    @RabbitListener(queues = "inventory.queue")
    public void handleOrderCreatedListener(Inventory inventory){
        System.out.println("order received"+inventory);
    }
}
