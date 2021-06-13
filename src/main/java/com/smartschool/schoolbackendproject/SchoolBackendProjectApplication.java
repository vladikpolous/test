package com.smartschool.schoolbackendproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SchoolBackendProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolBackendProjectApplication.class, args);
    }

}
