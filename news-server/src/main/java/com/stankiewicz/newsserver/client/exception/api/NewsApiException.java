package com.stankiewicz.newsserver.client.exception.api;

public class NewsApiException extends RuntimeException {
    public NewsApiException(String message) {
        super(message);
    }
}
