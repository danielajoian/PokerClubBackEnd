package com.codecool.pokerclubbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket swaggerConfiguration() {
        // return a prepared Docket instance
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/**"))
                .apis(RequestHandlerSelectors.basePackage("com.codecool.pokerclubbackend"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "PokerApp API",
                "API for PokerApp",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact(
                        "Daniela Joian",
                        "https://PokerApp.ro",
                        "daniela.joian@gmail.com"),
                "API Licence",
                "https://PokerApp.ro",
                Collections.emptyList()
        );
    }
}
