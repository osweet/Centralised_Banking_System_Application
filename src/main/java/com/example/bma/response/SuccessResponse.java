package com.example.bma.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SuccessResponse<T> {

    @JsonProperty("response-info")
    private final ResponseMetadata responseMetadata;

    @JsonProperty("response-data")
    private final ResponsePayload<T> responsePayload;
}
