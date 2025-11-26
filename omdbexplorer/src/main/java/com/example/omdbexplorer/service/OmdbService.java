package com.example.omdbexplorer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OmdbService {

    private final RestTemplate restTemplate;

    @Value("${omdb.api.url}")
    private String apiUrl;

    @Value("${omdb.api.key}")
    private String apiKey;

    public OmdbService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String searchByTitle(String title) {
        String url = apiUrl + "?apikey=" + apiKey + "&s=" + title;
        return restTemplate.getForObject(url, String.class);
    }

    public String getMovieDetails(String imdbID) {
        String url = apiUrl + "?apikey=" + apiKey + "&i=" + imdbID;
        return restTemplate.getForObject(url, String.class);
    }
}
