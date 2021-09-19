package com.example.bma.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponseModel {

    @JsonProperty("address_id")
    private String addressId;

    @JsonProperty("address_line")
    private String addressLine;

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
