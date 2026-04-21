package com.univesp.pji.inventory_manager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Inventory Manager API - UNIVESP")
                        .version("1.0")
                        .description("Sistema de Gerenciamento de Inventário para Materiais de Construção."));
    }
}