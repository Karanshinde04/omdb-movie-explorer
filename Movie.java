package com.example.omdbexplorer.model;

public class Movie {

    private String title;
    private String year;
    private String poster;

    public Movie(String title, String year, String poster) {
        this.title = title;
        this.year = year;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getPoster() {
        return poster;
    }
}
