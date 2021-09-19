package com.example.bma.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequestModel {

    @JsonProperty("address_line")
    private String line;

    @JsonProperty("address_landmark")
    private String landmark;

    @JsonProperty("address_city")
    private String city;

    @JsonProperty("address_state")
    private String state;

    @JsonProperty("address_country")
    private String country;

    @JsonProperty("address_zipcode")
    private String zipcode;
}
