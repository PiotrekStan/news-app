package com.stankiewicz.newsserver.client.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CodeDTO {

    API_KEY_INVALID("apiKeyInvalid"),
    API_KEY_MISSING("apiKeyMissing"),
    UNEXPECTED_ERROR("unexpectedError");

    private String text;

    @JsonCreator
    public static CodeDTO fromString(String text) {
        for (CodeDTO code : CodeDTO.values()) {
            if (code.text.equalsIgnoreCase(text)) {
                return code;
            }
        }
        return UNEXPECTED_ERROR;
    }
}
