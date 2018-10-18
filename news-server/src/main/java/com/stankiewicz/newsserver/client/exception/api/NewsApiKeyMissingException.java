package com.stankiewicz.newsserver.client.exception.api;

public class NewsApiKeyMissingException extends NewsApiException {
    public NewsApiKeyMissingException() {
        super("Your API key is missing from the request. Append it to the request.");
    }
}
