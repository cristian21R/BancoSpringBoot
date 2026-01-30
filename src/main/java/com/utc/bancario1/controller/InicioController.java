package com.utc.bancario1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/")  // Esto mapea la raíz del contexto de tu app
    public String index() {
        return "clientes"; // Va a buscar src/main/resources/templates/index.html
    }
}
