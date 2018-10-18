package com.stankiewicz.newsserver.service.impl;

import com.stankiewicz.newsserver.client.NewsApiClient;
import com.stankiewicz.newsserver.client.model.NewsResponseDTO;
import com.stankiewicz.newsserver.model.NewsDTO;
import com.stankiewicz.newsserver.service.NewsService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsServiceImpl.class);

    private final ModelMapper mapper;
    private final NewsApiClient newsApiClient;

    public NewsServiceImpl(ModelMapper mapper, NewsApiClient newsApiClient) {
        this.mapper = mapper;
        this.newsApiClient = newsApiClient;
    }

    @Override
    public NewsDTO getByCountryAndCategory(String country, String category) {
        LOGGER.info("getByCountryAndCategory: Looking for news country = {}, category = ", country, category);
        NewsResponseDTO responseDTO = newsApiClient.getNewsByCountryAndCategory(country, category);
        return mapper.map(responseDTO, NewsDTO.class);
    }

}
