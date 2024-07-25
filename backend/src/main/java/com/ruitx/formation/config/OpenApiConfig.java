package com.ruitx.formation.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OpenApiConfig {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("School API")
                        .description("Simple school API, with teachers, students and courses")
                        .version("0.0.1-SNAPSHOT")
                        .contact(new Contact().name("Rui Teixeira").email("rui.teixeira@minderaschoolacedemy.com"))
                );
    }
}
