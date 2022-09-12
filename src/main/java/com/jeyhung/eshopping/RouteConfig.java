package com.jeyhung.eshopping;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/identity/**").uri("http://localhost:8081/api/"))
                .route(r -> r.path("/api/payment/**").uri("http://localhost:8082/api/"))
                .route(r -> r.path("/api/catalog/**").uri("http://localhost:8083/api/"))
                .build();
    }

}
