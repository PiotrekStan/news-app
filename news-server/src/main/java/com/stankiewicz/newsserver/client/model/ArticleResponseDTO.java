package com.stankiewicz.newsserver.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stankiewicz.newsserver.client.util.ArticleDeserializer;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = ArticleDeserializer.class)
public class ArticleResponseDTO {

    private String author;

    private String title;

    private String description;

    private String date;

    private String sourceName;

    private String articleUrl;

    private String imageUrl;

}
