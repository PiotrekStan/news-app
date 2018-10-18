package com.stankiewicz.newsserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class ArticleDTO {

    private String author;
    private String title;
    private String description;
    private String date;
    private String sourceName;
    private String articleUrl;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String imageUrl;
}
