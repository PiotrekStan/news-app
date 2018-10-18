package com.stankiewicz.newsserver.utils.client;

import com.stankiewicz.newsserver.client.model.NewsResponseDTO;
import com.stankiewicz.newsserver.model.NewsDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class NewsResponseDtoAssertions {
    private NewsResponseDTO newsDTO;

    public static NewsResponseDtoAssertions assertDto(NewsResponseDTO dto) {
        assertThat(dto).isNotNull();
        return new NewsResponseDtoAssertions(dto);
    }

    public NewsResponseDtoAssertions hasCategory(String category) {
        assertThat(newsDTO.getCategory()).isEqualTo(category);
        return this;
    }

    public NewsResponseDtoAssertions hasCountry(String country) {
        assertThat(newsDTO.getCountry()).isEqualTo(country);
        return this;
    }

    public NewsResponseDtoAssertions hasArticles() {
        assertThat(newsDTO.getArticles()).isNotNull();
        return this;
    }

    public NewsResponseDtoAssertions hasArticlesWithSize(int size) {
        hasArticles();
        assertThat(newsDTO.getArticles().size()).isEqualTo(size);
        return this;
    }
}
