package com.stankiewicz.newsserver.client.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Factory for rest templates used by application
 */
@Configuration
public class RestTemplateFactory {

    @Value("${connect.timeout}")
    private int timeout;

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(timeout)
                .build();
    }

}