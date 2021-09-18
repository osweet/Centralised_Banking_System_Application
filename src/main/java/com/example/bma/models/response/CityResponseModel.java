package com.example.bma.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResponseModel {

    @JsonProperty("city_id")
    private String cityId;

    @JsonProperty("city_name")
    private String cityName;

    @JsonProperty("state_name")
    private String stateName;
}
