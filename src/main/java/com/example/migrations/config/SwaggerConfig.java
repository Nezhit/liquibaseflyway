package com.example.migrations.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Simple API",
                description = "A simple API to learn swagger annotations",
                version = "v0.1",
                license = @io.swagger.v3.oas.annotations.info.License(name = "MIT", url = "http://springdoc.org")
        )
)
public class SwaggerConfig {
}
