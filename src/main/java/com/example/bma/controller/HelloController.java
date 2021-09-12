package com.example.bma.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bma/api")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }
}
