package com.stankiewicz.newsserver.client;

import com.stankiewicz.newsserver.client.exception.api.NewsApiException;
import com.stankiewicz.newsserver.client.exception.api.NewsApiKeyInvalidException;
import com.stankiewicz.newsserver.client.exception.api.NewsApiKeyMissingException;
import com.stankiewicz.newsserver.client.exception.client.NewsClientException;
import com.stankiewicz.newsserver.client.exception.client.NewsClientUnauthorized;
import com.stankiewicz.newsserver.client.exception.server.NewsServerException;
import com.stankiewicz.newsserver.client.exception.server.NewsServiceUnavailable;
import com.stankiewicz.newsserver.client.model.NewsResponseDTO;
import com.stankiewicz.newsserver.utils.client.ArticleResponseDtoAssertions;
import com.stankiewicz.newsserver.utils.client.ClientExampleDataProvider;
import com.stankiewicz.newsserver.utils.client.NewsResponseDtoAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsApiClientTest {
    private MockRestServiceServer mockServer;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NewsApiClient newsApiClient;

    @Value("${news.api.key}")
    private String apiKey;

    private static final String API_URL_PREFIX = "https://newsapi.org/v2/top-headlines?country=pl&category=technology&apiKey=";
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

    private String API_URL;

    @Before
    public void setUp() {
        RestGatewaySupport gatewaySupport = new RestGatewaySupport();
        gatewaySupport.setRestTemplate(restTemplate);
        mockServer = MockRestServiceServer.createServer(restTemplate);
        this.API_URL = API_URL_PREFIX + apiKey;
    }

    @Test
    public void shouldReturnNewsResponseDto() {
        mockServer.expect(requestTo(this.API_URL))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(ClientExampleDataProvider.getJsonData(), MediaType.APPLICATION_JSON));

        NewsResponseDTO dto = newsApiClient.getNewsByCountryAndCategory(COUNTRY, CATEGORY);

        mockServer.verify();
        NewsResponseDtoAssertions.assertDto(dto)
                .hasCountry(COUNTRY)
                .hasCategory(CATEGORY)
                .hasArticlesWithSize(ARTICLES_SIZE);

        ArticleResponseDtoAssertions.assertDto(dto.getArticles().get(0))
                .hasAuthor(AUTHOR)
                .hasTitle(TITLE)
                .hasDescription(DESCRIPTION)
                .hasDate(DATE)
                .hasSourceName(SOURCE_NAME)
                .hasImageUrl(IMAGE_URL)
                .hasArticleUrl(ARTICLE_URL);
    }

    @Test(expected = NewsServerException.class)
    public void shouldReturnServerException() {
        mockServer.expect(requestTo(this.API_URL))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());

        newsApiClient.getNewsByCountryAndCategory(COUNTRY, CATEGORY);

        mockServer.verify();
    }

    @Test(expected = NewsClientUnauthorized.class)
    public void shouldReturnClientUnauthorizedException() {
        mockServer.expect(requestTo(this.API_URL))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.UNAUTHORIZED));

        newsApiClient.getNewsByCountryAndCategory(COUNTRY, CATEGORY);

        mockServer.verify();
    }

    @Test(expected = NewsServiceUnavailable.class)
    public void shouldReturnUnavailableException() {
        mockServer.expect(requestTo(this.API_URL))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.SERVICE_UNAVAILABLE));

        newsApiClient.getNewsByCountryAndCategory(COUNTRY, CATEGORY);

        mockServer.verify();
    }

    @Test(expected = NewsApiKeyInvalidException.class)
    public void shouldReturnApiKeyInvalidException() {
        mockServer.expect(requestTo(this.API_URL))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(ClientExampleDataProvider.getInvalidKeyJsonData(), MediaType.APPLICATION_JSON));

        newsApiClient.getNewsByCountryAndCategory(COUNTRY, CATEGORY);

        mockServer.verify();
    }

    @Test(expected = NewsApiKeyMissingException.class)
    public void shouldReturnApiKeyMissingException() {
        mockServer.expect(requestTo(this.API_URL))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(ClientExampleDataProvider.getMissingKeyJsonData(), MediaType.APPLICATION_JSON));

        newsApiClient.getNewsByCountryAndCategory(COUNTRY, CATEGORY);

        mockServer.verify();
    }

    @Test(expected = NewsApiException.class)
    public void shouldReturnApiException() {
        mockServer.expect(requestTo(this.API_URL))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(ClientExampleDataProvider.getUnexpectedErrorJsonData(), MediaType.APPLICATION_JSON));

        newsApiClient.getNewsByCountryAndCategory(COUNTRY, CATEGORY);

        mockServer.verify();
    }

}
