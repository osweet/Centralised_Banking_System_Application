package com.example.bma.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankResponseModel {

    @JsonProperty("bank_id")
    private String bankId;

    @JsonProperty("bank_name")
    private String bankName;

    @JsonProperty("bank_email")
    private String bankEmail;

    @JsonProperty("bank_address")
    private AddressResponseModel bankAddress;
}
