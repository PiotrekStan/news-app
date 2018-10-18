package com.stankiewicz.newsserver.service;

import com.stankiewicz.newsserver.client.NewsApiClient;
import com.stankiewicz.newsserver.client.model.ArticleResponseDTO;
import com.stankiewicz.newsserver.client.model.NewsResponseDTO;
import com.stankiewicz.newsserver.model.ArticleDTO;
import com.stankiewicz.newsserver.model.NewsDTO;
import com.stankiewicz.newsserver.utils.ArticleDtoAssertions;
import com.stankiewicz.newsserver.utils.NewsDtoAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceTest {
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

    @MockBean
    private NewsApiClient newsApiClient;

    @Test
    public void shouldReturnNewsProvidedByClient() {
        when(newsApiClient.getNewsByCountryAndCategory(COUNTRY, CATEGORY)).thenReturn(createMockDto());

        NewsDTO result = newsService.getByCountryAndCategory(COUNTRY, CATEGORY);

        NewsDtoAssertions.assertDto(result)
                .hasCountry(COUNTRY)
                .hasCategory(CATEGORY)
                .hasArticlesWithSize(ARTICLES_SIZE);

        ArticleDtoAssertions.assertDto(result.getArticles().get(0))
                .hasAuthor(AUTHOR)
                .hasTitle(TITLE)
                .hasDescription(DESCRIPTION)
                .hasDate(DATE)
                .hasSourceName(SOURCE_NAME)
                .hasImageUrl(IMAGE_URL)
                .hasArticleUrl(ARTICLE_URL);
    }

    @Autowired
    private NewsService newsService;

    NewsResponseDTO createMockDto(){
        NewsResponseDTO dto = new NewsResponseDTO();
        dto.setCountry(COUNTRY);
        dto.setCategory(CATEGORY);
        ArticleResponseDTO articleDTO = new ArticleResponseDTO();
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
