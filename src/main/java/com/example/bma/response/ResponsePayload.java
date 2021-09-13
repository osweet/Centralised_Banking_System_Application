package com.example.bma.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponsePayload<T> {

    @JsonProperty("data-body")
    private final T payload;
}
