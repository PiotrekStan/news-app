package com.stankiewicz.newsserver.client.exception.server;

public class NewsServiceUnavailable extends NewsServerException {
    public NewsServiceUnavailable() {
        super("Newsapi.org server is unavailable.");
    }
}
