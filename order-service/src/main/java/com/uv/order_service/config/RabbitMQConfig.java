package com.uv.order_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Inventory
    public static final String INVENTORY_EXCHANGE = "inventory.exchange";
    public static final String INVENTORY_QUEUE = "inventory.queue";
    public static final String INVENTORY_ROUTING_KEY = "inventory.queue";

    // Notification
    public static final String NOTIFICATION_EXCHANGE = "notification.exchange";
    public static final String NOTIFICATION_QUEUE = "notification.queue";
    public static final String NOTIFICATION_ROUTING_KEY = "notification.queue";


    // ---------------- INVENTORY ----------------

    @Bean
    public DirectExchange inventoryExchange() {
        return new DirectExchange(INVENTORY_EXCHANGE);
    }

    @Bean
    public Queue inventoryQueue() {
        return new Queue(INVENTORY_QUEUE);
    }

    @Bean
    public Binding inventoryBinding(
            Queue inventoryQueue,
            DirectExchange inventoryExchange) {

        return BindingBuilder
                .bind(inventoryQueue)
                .to(inventoryExchange)
                .with(INVENTORY_ROUTING_KEY);
    }
    // JSON converter
    @Bean
    public JacksonJsonMessageConverter jacksonJsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
    // ---------------- NOTIFICATION ----------------

    @Bean
    public DirectExchange notificationExchange() {
        return new DirectExchange(NOTIFICATION_EXCHANGE);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(NOTIFICATION_QUEUE);
    }

    @Bean
    public Binding notificationBinding(
            Queue notificationQueue,
            DirectExchange notificationExchange) {

        return BindingBuilder
                .bind(notificationQueue)
                .to(notificationExchange)
                .with(NOTIFICATION_ROUTING_KEY);
    }
}
