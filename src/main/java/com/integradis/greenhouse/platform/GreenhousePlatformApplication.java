package com.integradis.greenhouse.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GreenhousePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreenhousePlatformApplication.class, args);
    }

}
