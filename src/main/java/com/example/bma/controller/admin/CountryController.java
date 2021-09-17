package com.example.bma.controller.admin;

import com.example.bma.models.request.CountryRequestModel;
import com.example.bma.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bma/api/admin/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping("/add")
    public void addNewCountry(@RequestBody CountryRequestModel countryRequestModel) {

    }
}
