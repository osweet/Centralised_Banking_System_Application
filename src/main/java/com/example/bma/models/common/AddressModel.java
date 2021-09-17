package com.example.bma.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressModel {

    @JsonProperty("address_line1")
    private String line1;

    @JsonProperty("address_line2")
    private String line2;

    @JsonProperty("address_line3")
    private String line3;

    @JsonProperty("address_landmark")
    private String landmark;

    @JsonProperty("address_city")
    private String city;

    @JsonProperty("address_state")
    private String state;

    @JsonProperty("address_country")
    private String country;

    @JsonProperty("address_zipcode")
    private Integer zipcode;
}
