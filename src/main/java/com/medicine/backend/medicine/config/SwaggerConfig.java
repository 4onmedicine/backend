package com.medicine.backend.medicine.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Server server = new Server();
        server.setUrl("https://4onmserver.kro.kr/");

        return new OpenAPI()
                .components(new Components())
                .info(apiInfo())
                .servers(List.of(server));
    }

    private Info apiInfo() {
        return new Info()
                .title("Medicine API Documentation")
                .description("springdoc Swagger UI 테스트")
                .version("1.0.0");
    }
}
