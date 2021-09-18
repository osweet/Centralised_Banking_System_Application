package com.example.bma.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateRequestModel {

    @JsonProperty("state_name")
    private String stateName;

    @JsonProperty("country_name")
    private String countryName;
}
