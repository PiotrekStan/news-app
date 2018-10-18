package com.stankiewicz.newsserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class NewsDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String country;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String category;

    private List<ArticleDTO> articles;


}
