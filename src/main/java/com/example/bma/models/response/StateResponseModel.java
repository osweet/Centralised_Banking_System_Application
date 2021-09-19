package com.example.bma.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateResponseModel {

    @JsonProperty("state_id")
    private String stateId;

    @JsonProperty("state_name")
    private String stateName;

    @JsonProperty("country_name")
    private String countryName;
}
