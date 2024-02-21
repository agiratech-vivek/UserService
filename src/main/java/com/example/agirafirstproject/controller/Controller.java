package com.example.agirafirstproject.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello-world")
public class Controller {
    @GetMapping
    public String getHello(){
        return "Hello World";
    }
}