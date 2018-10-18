package com.stankiewicz.newsserver.utils;

import com.stankiewicz.newsserver.model.ArticleDTO;
import com.stankiewicz.newsserver.model.NewsDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class ArticleDtoAssertions {
    private ArticleDTO articleDTO;

    public static ArticleDtoAssertions assertDto(ArticleDTO dto) {
        assertThat(dto).isNotNull();
        return new ArticleDtoAssertions(dto);
    }

    public static ArticleDtoAssertions assertResponse(ResponseEntity<ArticleDTO> responseData) {
        assertThat(responseData.getStatusCode()).isEqualTo(HttpStatus.OK);
        return new ArticleDtoAssertions(responseData.getBody());
    }

    public ArticleDtoAssertions hasAuthor(String author) {
        assertThat(articleDTO.getAuthor()).isEqualTo(author);
        return this;
    }

    public ArticleDtoAssertions hasTitle(String title) {
        assertThat(articleDTO.getTitle()).isEqualTo(title);
        return this;
    }

    public ArticleDtoAssertions hasDescription(String description) {
        assertThat(articleDTO.getDescription()).isEqualTo(description);
        return this;
    }

    public ArticleDtoAssertions hasDate(String date) {
        assertThat(articleDTO.getDate()).isEqualTo(date);
        return this;
    }

    public ArticleDtoAssertions hasSourceName(String sourceName) {
        assertThat(articleDTO.getSourceName()).isEqualTo(sourceName);
        return this;
    }

    public ArticleDtoAssertions hasArticleUrl(String articleUrl) {
        assertThat(articleDTO.getArticleUrl()).isEqualTo(articleUrl);
        return this;
    }

    public ArticleDtoAssertions hasImageUrl(String imageUrl) {
        assertThat(articleDTO.getImageUrl()).isEqualTo(imageUrl);
        return this;
    }
}
