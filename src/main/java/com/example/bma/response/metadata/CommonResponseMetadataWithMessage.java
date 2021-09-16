package com.example.bma.response.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommonResponseMetadataWithMessage extends CommonResponseMetadata {

    @JsonProperty("response_message")
    private final String responseMessage;

    public CommonResponseMetadataWithMessage(Integer responseHttpStatusCode,
                                             String responseMessage) {
        super(responseHttpStatusCode);
        this.responseMessage = responseMessage;
    }
}
