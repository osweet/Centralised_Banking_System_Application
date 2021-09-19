package com.example.bma.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryRequestModel {

    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("country_short_name")
    private String countryShortName;
}
