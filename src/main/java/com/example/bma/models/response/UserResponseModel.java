package com.example.bma.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseModel {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_full_name")
    private String userFullName;

    @JsonProperty("user_contact")
    private Long userContact;

    @JsonProperty("user_email")
    private String userEmail;
}
