package dev.diegovsc42.MatchUp_API.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Match-UP API")
                        .version("1.0.0")
                        .description("API desenvolvida para gerenciamento de partidas")
                        .contact(new Contact()
                                .name("Suporte")
                                .email("2001.vieira.diego@gmail.com")));
    }
}