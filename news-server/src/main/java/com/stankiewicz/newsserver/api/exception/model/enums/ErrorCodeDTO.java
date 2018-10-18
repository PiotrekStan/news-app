package com.stankiewicz.newsserver.api.exception.model.enums;

public enum ErrorCodeDTO {
    INVALID_API_KEY,
    MISSING_API_KEY,
    NO_RESULTS,
    NEWS_API_UNAUTHORIZED,
    NEWS_API_UNAVAILABLE,
    CLIENT_CONNECTION_EXCEPTION,
    CLIENT_ERROR,
    SERVER_ERROR,
    UNEXPECTED_ERROR;
}
