package com.example.bma.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponseModel {

    @JsonProperty("role_id")
    private String roleId;

    @JsonProperty("role_name")
    private String roleName;

    @JsonProperty("role_description")
    private String roleDescription;
}
