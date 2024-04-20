package com.example.migrations.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Simple API")
                        .description("A simple API to learn swagger annotations")
                        .version("v0.1")
                        .license(new License().name("MIT").url("http://springdoc.org")));
    }
}
