package com.example.bma.response.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse<M> {

    @JsonProperty("error-info")
    private final M commonResponseMetadata;
}
