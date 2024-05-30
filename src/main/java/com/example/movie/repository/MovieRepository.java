// Write your code here

package com.example.movie.repository;

import java.util.*;
import com.example.movie.model.Movie;

public interface MovieRepository {
    ArrayList<Movie> allMovie();

    Movie eachMovie(int movieId);

    Movie addMovie(Movie movie);

    Movie updateMovie(int movieId, Movie mv);

    void deleteMovie(int movieId);
}