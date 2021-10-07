package com.codecool.pokerclubbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PokerClubBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokerClubBackendApplication.class, args);
    }
}
