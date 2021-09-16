package com.example.bma.response.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GetAllDataResponseMetadata extends CommonResponseMetadata {

    @JsonProperty("page_number")
    private final Integer pageNumber;

    @JsonProperty("page_size")
    private final Integer pageSize;

    public GetAllDataResponseMetadata(Integer responseHttpStatusCode,
                                      Integer pageNumber,
                                      Integer pageSize) {
        super(responseHttpStatusCode);
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
}
