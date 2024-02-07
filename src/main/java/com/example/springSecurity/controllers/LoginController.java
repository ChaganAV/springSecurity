package com.example.springSecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/login")
public class LoginController {
    @GetMapping("/login")
    public String registerForm(){
        return "login";
    }

    @GetMapping("/public-data")
    public String userPage(){
        return "public-data";
    }

    @GetMapping("/private-data")
    public String adminPage(){
        return "private-page";
    }

    @PostMapping("/login")
    public String register(){
        return "redirect:/private-data";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }
}
