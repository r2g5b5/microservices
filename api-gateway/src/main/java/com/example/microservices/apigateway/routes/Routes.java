package com.example.microservices.apigateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Map;

@Configuration
public class Routes {

    @Value("${service.urls.product}")
    private String productServiceUrl;

    @Value("${service.urls.order}")
    private String orderServiceUrl;

    @Value("${service.urls.inventory}")
    private String inventoryServiceUrl;

    @Bean
    public RouterFunction<ServerResponse> apiGatewayRoutes() {
        Map<String, String> routes = Map.of(
                "/api/product", productServiceUrl,
                "/api/order", orderServiceUrl,
                "/api/inventory", inventoryServiceUrl
        );

        var gatewayBuilder = GatewayRouterFunctions.route("api_gateway");

        routes.forEach((path, url) ->
                gatewayBuilder.route(RequestPredicates.path(path), HandlerFunctions.http(url)));

        return gatewayBuilder.build();
    }
}
