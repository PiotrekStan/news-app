package com.stankiewicz.newsserver.client.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.stankiewicz.newsserver.client.model.ArticleResponseDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Custom deserializer of response from newsapi.org which contains article data.
 */
public class ArticleDeserializer extends StdDeserializer<ArticleResponseDTO> {

    private static final String DATE_FORMAT = "yyyy-MM-DD";

    public ArticleDeserializer() {
        this(null);
    }

    public ArticleDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ArticleResponseDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode articleNode = jsonParser.getCodec().readTree(jsonParser);
        ArticleResponseDTO articleDTO = new ArticleResponseDTO();
        articleDTO.setAuthor(articleNode.get("author").textValue());
        articleDTO.setTitle(articleNode.get("title").textValue());
        articleDTO.setDescription(articleNode.get("description").textValue());
        articleDTO.setSourceName(articleNode.get("source").get("name").textValue());
        articleDTO.setArticleUrl(articleNode.get("url").textValue());
        articleDTO.setImageUrl(articleNode.get("urlToImage").textValue());
        articleDTO.setDate(getDateString(articleNode.get("publishedAt").textValue()));

        return articleDTO;
    }

    private String getDateString(String input) {
        return LocalDate.parse(input, DateTimeFormatter.ISO_OFFSET_DATE_TIME).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
