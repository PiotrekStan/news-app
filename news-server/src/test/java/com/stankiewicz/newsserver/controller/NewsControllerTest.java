package com.stankiewicz.newsserver.controller;

import com.stankiewicz.newsserver.api.exception.model.ErrorResponseDTO;
import com.stankiewicz.newsserver.api.exception.model.enums.ErrorCodeDTO;
import com.stankiewicz.newsserver.client.exception.api.NewsApiException;
import com.stankiewicz.newsserver.client.exception.api.NewsApiKeyInvalidException;
import com.stankiewicz.newsserver.client.exception.api.NewsApiKeyMissingException;
import com.stankiewicz.newsserver.client.exception.client.NewsClientConnectionException;
import com.stankiewicz.newsserver.client.exception.client.NewsClientException;
import com.stankiewicz.newsserver.client.exception.server.NewsServerException;
import com.stankiewicz.newsserver.client.exception.client.NewsClientUnauthorized;
import com.stankiewicz.newsserver.client.exception.server.NewsServiceUnavailable;
import com.stankiewicz.newsserver.model.ArticleDTO;
import com.stankiewicz.newsserver.model.NewsDTO;
import com.stankiewicz.newsserver.service.NewsService;
import com.stankiewicz.newsserver.utils.ArticleDtoAssertions;
import com.stankiewicz.newsserver.utils.ErrorResponseDtoAssertions;
import com.stankiewicz.newsserver.utils.NewsDtoAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewsControllerTest {
    private static final String CATEGORY = "technology";
    private static final String COUNTRY = "pl";
    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String DATE = "1970-01-01";
    private static final String SOURCE_NAME = "SOURCE.COM";
    private static final String ARTICLE_URL = "http://article.url";
    private static final String IMAGE_URL = "http://image.url";
    private static final int ARTICLES_SIZE = 1;
    private static final String REST_URL = "/rest/api/news/pl/technology";

    private static final String NEWS_CLIENT_CONN_EXC_MSG = "Client connection exception message";
    private static final String NEWS_API_UNAUTHORIZED_EXC_MSG = "Unauthorized. Your API key was missing from the request, or wasn't correct.";
    private static final String NEWS_API_UNAVAILABLE_EXC_MSG = "Newsapi.org server is unavailable.";
    private static final String NEWS_API_INVALID_KEY_EXC_MSG = "Your API key hasn't been entered correctly. Double check it and try again.";
    private static final String NEWS_API_MISSING_KEY_EXC_MSG = "Your API key is missing from the request. Append it to the request.";
    private static final String SERVER_EXC_MSG = "Server exception message";
    private static final String API_EXC_MSG = "Api exception message";
    private static final String CLIENT_EXC_MSG = "Client exception message";

    @MockBean
    private NewsService newsService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnNewsDtoWithOkStatus() {
        when(newsService.getByCountryAndCategory(COUNTRY,CATEGORY)).thenReturn(createMockDto());
        ResponseEntity<NewsDTO> response = restTemplate.getForEntity(REST_URL, NewsDTO.class);

        NewsDtoAssertions.assertResponse(response)
                .hasCountry(COUNTRY)
                .hasCategory(CATEGORY)
                .hasArticlesWithSize(ARTICLES_SIZE);

        ArticleDtoAssertions.assertDto(response.getBody().getArticles().get(0))
                .hasAuthor(AUTHOR)
                .hasTitle(TITLE)
                .hasDescription(DESCRIPTION)
                .hasDate(DATE)
                .hasSourceName(SOURCE_NAME)
                .hasImageUrl(IMAGE_URL)
                .hasArticleUrl(ARTICLE_URL);
    }

    @Test
    public void shouldReturnClientConnectionException() {
        when(newsService.getByCountryAndCategory(COUNTRY, CATEGORY)).thenThrow(new NewsClientConnectionException(NEWS_CLIENT_CONN_EXC_MSG));
        ResponseEntity<ErrorResponseDTO> response = restTemplate.getForEntity(REST_URL, ErrorResponseDTO.class);

        ErrorResponseDtoAssertions.assertResponse(response)
                .hasStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .hasCodeType(ErrorCodeDTO.CLIENT_CONNECTION_EXCEPTION)
                .hasCode(ErrorCodeDTO.CLIENT_CONNECTION_EXCEPTION.ordinal())
                .hasMessage(NEWS_CLIENT_CONN_EXC_MSG);
    }

    @Test
    public void shouldReturnUnauthorizedException() {
        when(newsService.getByCountryAndCategory(COUNTRY, CATEGORY)).thenThrow(new NewsClientUnauthorized());
        ResponseEntity<ErrorResponseDTO> response = restTemplate.getForEntity(REST_URL, ErrorResponseDTO.class);

        ErrorResponseDtoAssertions.assertResponse(response)
                .hasStatus(HttpStatus.UNAUTHORIZED)
                .hasCodeType(ErrorCodeDTO.NEWS_API_UNAUTHORIZED)
                .hasCode(ErrorCodeDTO.NEWS_API_UNAUTHORIZED.ordinal())
                .hasMessage(NEWS_API_UNAUTHORIZED_EXC_MSG);
    }

    @Test
    public void shouldReturnUnavailableException() {
        when(newsService.getByCountryAndCategory(COUNTRY, CATEGORY)).thenThrow(new NewsServiceUnavailable());
        ResponseEntity<ErrorResponseDTO> response = restTemplate.getForEntity(REST_URL, ErrorResponseDTO.class);

        ErrorResponseDtoAssertions.assertResponse(response)
                .hasStatus(HttpStatus.SERVICE_UNAVAILABLE)
                .hasCodeType(ErrorCodeDTO.NEWS_API_UNAVAILABLE)
                .hasCode(ErrorCodeDTO.NEWS_API_UNAVAILABLE.ordinal())
                .hasMessage(NEWS_API_UNAVAILABLE_EXC_MSG);
    }

    @Test
    public void shouldReturnApiKeyInvalidException() {
        when(newsService.getByCountryAndCategory(COUNTRY, CATEGORY)).thenThrow(new NewsApiKeyInvalidException());
        ResponseEntity<ErrorResponseDTO> response = restTemplate.getForEntity(REST_URL, ErrorResponseDTO.class);

        ErrorResponseDtoAssertions.assertResponse(response)
                .hasStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .hasCodeType(ErrorCodeDTO.INVALID_API_KEY)
                .hasCode(ErrorCodeDTO.INVALID_API_KEY.ordinal())
                .hasMessage(NEWS_API_INVALID_KEY_EXC_MSG);
    }

    @Test
    public void shouldReturnApiKeyMissingException() {
        when(newsService.getByCountryAndCategory(COUNTRY, CATEGORY)).thenThrow(new NewsApiKeyMissingException());
        ResponseEntity<ErrorResponseDTO> response = restTemplate.getForEntity(REST_URL, ErrorResponseDTO.class);

        ErrorResponseDtoAssertions.assertResponse(response)
                .hasStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .hasCodeType(ErrorCodeDTO.MISSING_API_KEY)
                .hasCode(ErrorCodeDTO.MISSING_API_KEY.ordinal())
                .hasMessage(NEWS_API_MISSING_KEY_EXC_MSG);
    }

    @Test
    public void shouldReturnServerException() {
        when(newsService.getByCountryAndCategory(COUNTRY, CATEGORY)).thenThrow(new NewsServerException(SERVER_EXC_MSG));
        ResponseEntity<ErrorResponseDTO> response = restTemplate.getForEntity(REST_URL, ErrorResponseDTO.class);

        ErrorResponseDtoAssertions.assertResponse(response)
                .hasStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .hasCodeType(ErrorCodeDTO.SERVER_ERROR)
                .hasCode(ErrorCodeDTO.SERVER_ERROR.ordinal())
                .hasMessage(SERVER_EXC_MSG);
    }

    @Test
    public void shouldReturnClientException() {
        when(newsService.getByCountryAndCategory(COUNTRY, CATEGORY)).thenThrow(new NewsClientException(CLIENT_EXC_MSG));
        ResponseEntity<ErrorResponseDTO> response = restTemplate.getForEntity(REST_URL, ErrorResponseDTO.class);

        ErrorResponseDtoAssertions.assertResponse(response)
                .hasStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .hasCodeType(ErrorCodeDTO.CLIENT_ERROR)
                .hasCode(ErrorCodeDTO.CLIENT_ERROR.ordinal())
                .hasMessage(CLIENT_EXC_MSG);
    }

    @Test
    public void shouldReturnApiException() {
        when(newsService.getByCountryAndCategory(COUNTRY, CATEGORY)).thenThrow(new NewsApiException(API_EXC_MSG));
        ResponseEntity<ErrorResponseDTO> response = restTemplate.getForEntity(REST_URL, ErrorResponseDTO.class);

        ErrorResponseDtoAssertions.assertResponse(response)
                .hasStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .hasCodeType(ErrorCodeDTO.UNEXPECTED_ERROR)
                .hasCode(ErrorCodeDTO.UNEXPECTED_ERROR.ordinal())
                .hasMessage(API_EXC_MSG);
    }

    NewsDTO createMockDto(){
        NewsDTO dto = new NewsDTO();
        dto.setCountry(COUNTRY);
        dto.setCategory(CATEGORY);
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setAuthor(AUTHOR);
        articleDTO.setTitle(TITLE);
        articleDTO.setDescription(DESCRIPTION);
        articleDTO.setDate(DATE);
        articleDTO.setSourceName(SOURCE_NAME);
        articleDTO.setArticleUrl(ARTICLE_URL);
        articleDTO.setImageUrl(IMAGE_URL);
        dto.setArticles(Arrays.asList(articleDTO));
        return dto;
    }

}
