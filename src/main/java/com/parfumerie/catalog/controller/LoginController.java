package com.parfumerie.catalog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // Gestioneaza afisarea paginii de login (URL-ul specificat în SecurityConfig)
    @GetMapping("/login")
    public String login() {
        // Returneaza numele template-ului Thymeleaf: login.html
        return "login";
    }

    // Pagina de baza (optional, dar util)
    @GetMapping("/")
    public String home() {
        return "redirect:/parfumuri";
    }
}