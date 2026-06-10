package com.example.qlsv_springboot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OpenApiConfig {

        private static final String SECURITY_SCHEME_NAME = "bearerAuth";

        @Bean
        public OpenAPI qlsvOpenAPI() {
                return new OpenAPI()
                                .info(new Info()
                                                .title("API Quản lý sinh viên")
                                                .version("1.0.0")
                                                .description("Tài liệu API hệ thống quản lý sinh viên"))
                                .addSecurityItem(new SecurityRequirement()
                                                .addList(SECURITY_SCHEME_NAME))
                                .components(new Components()
                                                .addSecuritySchemes(
                                                                SECURITY_SCHEME_NAME,
                                                                new SecurityScheme()
                                                                                .name(SECURITY_SCHEME_NAME)
                                                                                .type(SecurityScheme.Type.HTTP)
                                                                                .scheme("bearer")
                                                                                .bearerFormat("JWT")));
        }
}