package com.example.bma.response.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CommonResponseMetadata {

    @JsonProperty("response-code")
    final Integer responseHttpStatusCode;

    public CommonResponseMetadata(Integer responseHttpStatusCode) {
        this.responseHttpStatusCode = responseHttpStatusCode;
    }
}
