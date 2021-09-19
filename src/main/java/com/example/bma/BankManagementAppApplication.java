package com.example.bma;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Centralised Banking System Application",
        version = "0.1"))
public class BankManagementAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankManagementAppApplication.class, args);
    }

}
