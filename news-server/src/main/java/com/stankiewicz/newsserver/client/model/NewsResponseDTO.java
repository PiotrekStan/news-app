package com.stankiewicz.newsserver.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stankiewicz.newsserver.client.model.enums.CodeDTO;
import com.stankiewicz.newsserver.client.model.enums.StatusDTO;
import lombok.Data;

import java.util.List;

@Data
public class NewsResponseDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String category;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String country;

    private StatusDTO status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CodeDTO code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    private List<ArticleResponseDTO> articles;

}
