package com.stankiewicz.newsserver.utils.client;

import com.stankiewicz.newsserver.client.model.ArticleResponseDTO;
import com.stankiewicz.newsserver.model.ArticleDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class ArticleResponseDtoAssertions {
    private ArticleResponseDTO articleDTO;

    public static ArticleResponseDtoAssertions assertDto(ArticleResponseDTO dto) {
        assertThat(dto).isNotNull();
        return new ArticleResponseDtoAssertions(dto);
    }

    public ArticleResponseDtoAssertions hasAuthor(String author) {
        assertThat(articleDTO.getAuthor()).isEqualTo(author);
        return this;
    }

    public ArticleResponseDtoAssertions hasTitle(String title) {
        assertThat(articleDTO.getTitle()).isEqualTo(title);
        return this;
    }

    public ArticleResponseDtoAssertions hasDescription(String description) {
        assertThat(articleDTO.getDescription()).isEqualTo(description);
        return this;
    }

    public ArticleResponseDtoAssertions hasDate(String date) {
        assertThat(articleDTO.getDate()).isEqualTo(date);
        return this;
    }

    public ArticleResponseDtoAssertions hasSourceName(String sourceName) {
        assertThat(articleDTO.getSourceName()).isEqualTo(sourceName);
        return this;
    }

    public ArticleResponseDtoAssertions hasArticleUrl(String articleUrl) {
        assertThat(articleDTO.getArticleUrl()).isEqualTo(articleUrl);
        return this;
    }

    public ArticleResponseDtoAssertions hasImageUrl(String imageUrl) {
        assertThat(articleDTO.getImageUrl()).isEqualTo(imageUrl);
        return this;
    }
}
