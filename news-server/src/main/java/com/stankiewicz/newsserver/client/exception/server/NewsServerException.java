package com.stankiewicz.newsserver.client.exception.server;

public class NewsServerException extends RuntimeException {
    public NewsServerException(String message) {
        super(message);
    }
}
