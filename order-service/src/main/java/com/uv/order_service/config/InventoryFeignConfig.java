package com.uv.order_service.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryFeignConfig {
    @Bean
    Logger.Level feignInvLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
