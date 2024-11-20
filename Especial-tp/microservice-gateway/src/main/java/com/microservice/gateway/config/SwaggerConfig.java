package com.microservice.gateway.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Login",
                version = "1.0.0",
                contact = @Contact(
                        name = "Grupo 32",
                        url = "https://github.com/Agustin-Gonzalorena/Arqui-Grupo32"
                ),
                description = "Api alquiler de monopatines electrónicos"
        )
)
public class SwaggerConfig {
}
