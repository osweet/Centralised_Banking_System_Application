package com.example.bma.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bma/api")
public class TestController {

    @GetMapping("/user")
    public String user() {
        return "Welcome, User";
    }

    @GetMapping("/admin")
    public String admin() {
        return "You must be an admin";
    }
}
