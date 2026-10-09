package br.com.avaliacao.api_telemetria.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configurações globais de documentação OpenAPI (Swagger)
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API de telemetria")
                        .version("1.0")
                        .description("Api REST desenvolvida para o CRUD de motores, setores, telemetrias e alertas"));
    }
}