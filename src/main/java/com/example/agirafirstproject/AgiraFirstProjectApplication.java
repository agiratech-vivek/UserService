package com.example.agirafirstproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AgiraFirstProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgiraFirstProjectApplication.class, args);
        System.out.println("Hello World");
    }
}
