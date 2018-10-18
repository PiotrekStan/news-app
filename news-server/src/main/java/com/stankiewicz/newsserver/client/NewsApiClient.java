package com.stankiewicz.newsserver.client;

import com.stankiewicz.newsserver.client.exception.api.NewsApiException;
import com.stankiewicz.newsserver.client.exception.api.NewsApiKeyInvalidException;
import com.stankiewicz.newsserver.client.exception.api.NewsApiKeyMissingException;
import com.stankiewicz.newsserver.client.exception.client.NewsClientConnectionException;
import com.stankiewicz.newsserver.client.exception.client.NewsClientException;
import com.stankiewicz.newsserver.client.exception.server.NewsServerException;
import com.stankiewicz.newsserver.client.exception.client.NewsClientUnauthorized;
import com.stankiewicz.newsserver.client.exception.server.NewsServiceUnavailable;
import com.stankiewicz.newsserver.client.model.NewsResponseDTO;
import com.stankiewicz.newsserver.client.model.enums.CodeDTO;
import com.stankiewicz.newsserver.client.model.enums.StatusDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Client for calling newsapi.org
 */
@Service
public class NewsApiClient {

    private final Logger LOGGER = LoggerFactory.getLogger(NewsApiClient.class);
    private final RestTemplate restTemplate;

    public NewsApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${news.api.key}")
    private String apiKey;

    @Value("${news.api.url}")
    private String url;

    private static final String CATEGORY_PARAM = "category";
    private static final String COUNTRY_PARAM = "country";
    private static final String API_KEY_PARAM = "apiKey";

    /**
     * @param country symbol name of country which should be origin of news e.g. pl/de/fr etc.
     * @param category category of news e.g. general, technology, health etc.
     * @return news data, which contains list of articles {@link NewsResponseDTO}
     * @throws NewsApiException             if unexpected data from newsapi.org was returned
     * @throws NewsApiKeyMissingException   if api key is missing
     * @throws NewsApiKeyInvalidException   if api key is invalid
     * @throws NewsClientException              if client side exception occurs
     * @throws NewsClientConnectionException    if error with connection on client side occurs
     * @throws NewsClientUnauthorized           if client is unauthorized
     * @throws NewsServerException          if error on server side occurs
     * @throws NewsServiceUnavailable       if service provided by newsapi.org is unavailable
     */
    public NewsResponseDTO getNewsByCountryAndCategory(String country, String category){
        NewsResponseDTO result = null;
        String requestUrl = buildRequestUrl(country, category);
        try {
            result = restTemplate.getForObject(requestUrl, NewsResponseDTO.class);
            LOGGER.info("getNewsByCountryAndCategory: Http GET request with params: country = {},category = {}, sent to url = {}", country, category, requestUrl);
            if (StatusDTO.ERROR.equals(result.getStatus())) {
                handleApiException(result.getCode(), result.getMessage());
            }
            result.setCountry(country);
            result.setCategory(category);
        } catch (ResourceAccessException e) {
            handleResourceAccessException(e);
        } catch (HttpClientErrorException e) {
            handleClientException(e);
        } catch (HttpServerErrorException e) {
            handleServerException(e);
        }
        return result;
    }

    private void handleApiException(CodeDTO code, String message) {
        LOGGER.error("NewsApiException occured with code = {} and message = {}", code, message);
        if (CodeDTO.API_KEY_INVALID.equals(code)) {
            throw new NewsApiKeyInvalidException();
        } else if (CodeDTO.API_KEY_MISSING.equals(code)) {
            throw new NewsApiKeyMissingException();
        } else if (CodeDTO.UNEXPECTED_ERROR.equals(code)) {
            throw new NewsApiException(message);
        }
    }

    private void handleResourceAccessException(ResourceAccessException e) {
        LOGGER.error("ResourceAccessException with message = {}", e.getLocalizedMessage());
        throw new NewsClientConnectionException(e.getLocalizedMessage());
    }

    private void handleClientException(HttpClientErrorException e) {
        LOGGER.error("HttpClientErrorException with message = {}", e.getLocalizedMessage());
        if (HttpStatus.UNAUTHORIZED.equals(e.getStatusCode())) {
            throw new NewsClientUnauthorized();
        }
        throw new NewsClientException(e.getLocalizedMessage());
    }

    private void handleServerException(HttpServerErrorException e) {
        LOGGER.error("HttpServerErrorException with message = {}", e.getLocalizedMessage());
        if (HttpStatus.SERVICE_UNAVAILABLE.equals(e.getStatusCode())) {
            throw new NewsServiceUnavailable();
        }
        throw new NewsServerException(e.getLocalizedMessage());
    }

    private String buildRequestUrl(String country, String category) {
        return UriComponentsBuilder.fromUriString(url)
                .queryParam(COUNTRY_PARAM, country)
                .queryParam(CATEGORY_PARAM, category)
                .queryParam(API_KEY_PARAM, apiKey)
                .build()
                .encode()
                .toString();
    }


}
