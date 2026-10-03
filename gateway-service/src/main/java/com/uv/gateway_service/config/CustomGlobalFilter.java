package com.uv.gateway_service.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class CustomGlobalFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerWebExchange modifiedExchange = exchange.mutate()
                .request(request -> request.headers(headers -> {
                    headers.add("X-test-name", "vaibhav");
                }))
                .build();
        return chain.filter(modifiedExchange);
    }
}
