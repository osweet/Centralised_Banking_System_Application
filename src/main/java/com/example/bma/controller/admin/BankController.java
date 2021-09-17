package com.example.bma.controller.admin;

import com.example.bma.models.request.BankRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bma/api/admin/banks")
public class BankController {

    @PostMapping("/add")
    public ResponseEntity<?> registerNewBank(@RequestBody BankRequestModel bankRequestModel) {
        return null;
    }
}
