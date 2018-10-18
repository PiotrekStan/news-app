package com.stankiewicz.newsserver.utils;

import com.stankiewicz.newsserver.model.NewsDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class NewsDtoAssertions {
    private NewsDTO newsDTO;

    public static NewsDtoAssertions assertDto(NewsDTO dto) {
        assertThat(dto).isNotNull();
        return new NewsDtoAssertions(dto);
    }

    public static NewsDtoAssertions assertResponse(ResponseEntity<NewsDTO> responseData) {
        assertThat(responseData.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseData.getBody()).isNotNull();
        return new NewsDtoAssertions(responseData.getBody());
    }

    public NewsDtoAssertions hasCategory(String category) {
        assertThat(newsDTO.getCategory()).isEqualTo(category);
        return this;
    }

    public NewsDtoAssertions hasCountry(String country) {
        assertThat(newsDTO.getCountry()).isEqualTo(country);
        return this;
    }

    public NewsDtoAssertions hasArticles() {
        assertThat(newsDTO.getArticles()).isNotNull();
        return this;
    }

    public NewsDtoAssertions hasArticlesWithSize(int size) {
        hasArticles();
        assertThat(newsDTO.getArticles().size()).isEqualTo(size);
        return this;
    }
}
