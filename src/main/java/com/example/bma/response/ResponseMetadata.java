package com.example.bma.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseMetadata {

    @JsonProperty("response-code")
    private final int responseHttpStatusCode;

    @JsonProperty("response-message")
    private final String responseMessage;
}
