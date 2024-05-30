package com.example.movie.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

import com.example.movie.service.MovieH2Service;
import com.example.movie.model.Movie;

// Write your code here

@RestController
public class MovieController {
    @Autowired
    public MovieH2Service jt;

    @GetMapping("/movies")
    public ArrayList<Movie> allMovie() {
        return jt.allMovie();
    }

    @GetMapping("/movies/{movieId}")
    public Movie eachMovie(@PathVariable("movieId") int movieId) {
        return jt.eachMovie(movieId);
    }

    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie movie) {
        return jt.addMovie(movie);
    }

    @PutMapping("/movies/{movieId}")
    public Movie updateMovie(@PathVariable("movieId") int movieId, @RequestBody Movie mv) {
        return jt.updateMovie(movieId, mv);
    }

    @DeleteMapping("/movies/{movieId}")
    public void deleteMovie(@PathVariable int movieId) {
        jt.deleteMovie(movieId);
    }
}