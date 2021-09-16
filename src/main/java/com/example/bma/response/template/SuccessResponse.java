package com.example.bma.response.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SuccessResponse<M, P> {

    @JsonProperty("response-info")
    private final M responseMetadata;

    @JsonProperty("response-data")
    private final P responsePayload;
}
