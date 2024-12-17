package com.example.microservices.apigateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;
import java.util.Map;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

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

        var gatewayBuilder = route("api_gateway");

        routes.forEach((path, url) ->
                gatewayBuilder.route(RequestPredicates.path(path), HandlerFunctions.http(url))
                        .filter(CircuitBreakerFilterFunctions.circuitBreaker(url,
                                URI.create("forward:/fallbackRoute"))));
        return gatewayBuilder.build();
    }

    @Bean
    public RouterFunction<ServerResponse> swaggerRoutes() {
        Map<String, String> swaggerRoutes = Map.of(
                "/aggregate/product-service/api-docs", productServiceUrl,
                "/aggregate/order-service/api-docs", orderServiceUrl,
                "/aggregate/inventory-service/api-docs", inventoryServiceUrl
        );

        var swaggerBuilder = route("swagger_routes");

        swaggerRoutes.forEach((path, url) ->
                swaggerBuilder.route(RequestPredicates.path(path), HandlerFunctions.http(url))
                        .filter(CircuitBreakerFilterFunctions.circuitBreaker(url,
                                URI.create("forward:/fallbackRoute")))
                        .filter(setPath("/api-docs")));

        return swaggerBuilder.build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return route("fallbackRoute")
                .GET("/fallbackRoute", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Service Unavailable, please try again later"))
                .build();
    }


}
