package com.example.omdbexplorer.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*") // allow frontend access
public class MovieController {

    private final RestTemplate restTemplate;
    private final String API_KEY = "c29e403d"; // Replace with your OMDB API key

    public MovieController() {
        this.restTemplate = new RestTemplate();
    }

    // Search movies by title
    @GetMapping("/search")
    public Map<String, Object> searchMovies(@RequestParam String title) {
        String url = "https://www.omdbapi.com/?apikey=" + API_KEY + "&s=" + title;
        return restTemplate.getForObject(url, Map.class);
    }

    // Get detailed info for a single movie
    @GetMapping("/details")
    public Map<String, Object> movieDetails(@RequestParam String imdbID) {
        String url = "https://www.omdbapi.com/?apikey=" + API_KEY + "&i=" + imdbID + "&plot=full";
        return restTemplate.getForObject(url, Map.class);
    }
}
