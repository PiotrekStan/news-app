package com.stankiewicz.newsserver.service;

import com.stankiewicz.newsserver.model.NewsDTO;

public interface NewsService {

    NewsDTO getByCountryAndCategory(String country, String category);

}
