package com.example.bma.models.request;

import com.example.bma.models.common.AddressModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankRequestModel {

    @JsonProperty("bank_name")
    private String bankName;

    @JsonProperty("bank_email")
    private String bankEmail;

    @JsonProperty("bank_address")
    private AddressModel bankAddress;
}
