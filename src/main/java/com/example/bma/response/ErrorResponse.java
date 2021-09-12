package com.example.bma.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {

    @JsonProperty("error-info")
    private final ResponseMetadata responseMetadata;
}
