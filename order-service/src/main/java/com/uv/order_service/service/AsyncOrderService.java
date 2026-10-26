package com.uv.order_service.service;

import com.uv.order_service.config.RabbitMQConfig;
import com.uv.order_service.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncOrderService {

    private final RabbitTemplate rabbitTemplate;

    public void addInventory(Inventory inventory) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.INVENTORY_EXCHANGE,RabbitMQConfig.INVENTORY_ROUTING_KEY,inventory);
    }
}
