package com.example.microservices.orderservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI orderServiceOpenApi() {
        return new OpenAPI()
                .info(new Info().title("Order Service API")
                        .version("0.1.0")
                        .description("Rest Api for Order Service")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

}
