package com.stankiewicz.newsserver.api.news;

import com.stankiewicz.newsserver.model.NewsDTO;
import com.stankiewicz.newsserver.service.NewsService;
import com.stankiewicz.newsserver.service.impl.NewsServiceImpl;
import org.springframework.web.bind.annotation.*;

/**
 * News controller
 */
@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/rest/api/news/{country}/{category}")
    public NewsDTO getByCategoryAndCountry(@PathVariable("country") String country, @PathVariable("category") String category) {
        return this.newsService.getByCountryAndCategory(country, category);
    }

}
