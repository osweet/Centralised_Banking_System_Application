package com.example.bma.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestModel {

    @JsonProperty("user_first_name")
    private String userFirstName;

    @JsonProperty("user_middle_name")
    private String userMiddleName;

    @JsonProperty("user_last_name")
    private String userLastName;

    @JsonProperty("user_contact")
    private Long userContact;

    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("user_password")
    private String userPassword;
}
