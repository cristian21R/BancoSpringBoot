package com.utc.bancario1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Bancario1Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Bancario1Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // Esto es necesario para desplegar en Tomcat externo
        return builder.sources(Bancario1Application.class);
    }
}
