package com.stankiewicz.newsserver.client.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum StatusDTO {
    @JsonProperty("ok")
    OK,
    @JsonProperty("error")
    ERROR
}
