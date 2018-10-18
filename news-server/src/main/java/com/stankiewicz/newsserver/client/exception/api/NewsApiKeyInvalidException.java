package com.stankiewicz.newsserver.client.exception.api;

public class NewsApiKeyInvalidException extends NewsApiException {
    public NewsApiKeyInvalidException() {
        super("Your API key hasn't been entered correctly. Double check it and try again.");
    }
}
