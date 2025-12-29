package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean(name = "customOpenAPIConfig")   // 🔥 renamed bean
    public OpenAPI openAPIConfig() {       // 🔥 renamed method
        return new OpenAPI()
                .servers(List.of(
                        new Server().url("https://9283.408procr.amypo.ai/")
                ));
    }
}