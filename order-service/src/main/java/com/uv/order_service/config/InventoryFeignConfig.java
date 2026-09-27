package com.uv.order_service.config;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class InventoryFeignConfig {
    @Bean
    Logger.Level feignInvLoggerLevel() {
        return Logger.Level.BASIC;
    }

    @Bean
    Request.Options options() {
        return new Request.Options(Duration.ofSeconds(3L), Duration.ofSeconds(5L), true);
    }

    @Bean
    Retryer retryer() {
        return new Retryer.Default(100L, 200L, 3);
    }
}
