package com.example.bma.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequestModel {

    @JsonProperty("role_name")
    private String roleName;

    @JsonProperty("role_description")
    private String roleDescription;
}
