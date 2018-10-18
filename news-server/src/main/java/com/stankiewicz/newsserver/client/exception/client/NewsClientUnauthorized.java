package com.stankiewicz.newsserver.client.exception.client;

import com.stankiewicz.newsserver.client.exception.server.NewsServerException;

public class NewsClientUnauthorized extends NewsServerException {
    public NewsClientUnauthorized() {
        super("Unauthorized. Your API key was missing from the request, or wasn't correct.");
    }
}
