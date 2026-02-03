package com.utc.bancario1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Bancario1Application extends SpringBootServletInitializer { // <--- 1. HEREDA DE ESTO

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) { // <--- 2. AGREGA ESTE MÉTODO
        return application.sources(Bancario1Application.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(Bancario1Application.class, args);
	}

}