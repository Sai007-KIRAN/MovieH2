package com.example.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import org.springframework.dao.EmptyResultDataAccessException;
import com.example.movie.model.Movie;
import com.example.movie.model.MovieRowMapper;
import com.example.movie.repository.MovieRepository;

// Write your code here

@Service
public class MovieH2Service implements MovieRepository {
    @Autowired
    public JdbcTemplate jd;

    @Override
    public ArrayList<Movie> allMovie() {
        List<Movie> movieList = jd.query("SELECT * FROM MOVIELIST", new MovieRowMapper());
        ArrayList<Movie> ListMovie = new ArrayList<>(movieList);
        return ListMovie;
    }

    @Override
    public Movie eachMovie(int movieId) {
        try {
            Movie MovieEach = jd.queryForObject("SELECT * FROM MOVIELIST WHERE movieId = ?", new MovieRowMapper(),
                    movieId);
            return MovieEach;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Movie addMovie(Movie movie) {
        jd.update("INSERT INTO MOVIELIST(movieName, leadActor) VALUES(?, ?)", movie.getMovieName(),
                movie.getLeadActor());
        Movie AddingMovie = jd.queryForObject("Select * From MOVIELIST WHERE movieName = ? AND leadActor = ?",
                new MovieRowMapper(), movie.getMovieName(), movie.getLeadActor());
        return AddingMovie;
    }

    @Override
    public Movie updateMovie(int movieId, Movie mv) {
        try {
            Movie existing = jd.queryForObject("SELECT * FROM MOVIELIST WHERE movieId = ?", new MovieRowMapper(),
                    movieId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (mv.getMovieName() != null) {
            jd.update("UPDATE MOVIELIST SET movieName = ? WHERE movieId = ?", mv.getMovieName(), movieId);
        }
        if (mv.getLeadActor() != null) {
            jd.update("UPDATE MOVIELIST SET leadActor = ? WHERE movieId = ?", mv.getLeadActor(), movieId);
        }
        return eachMovie(movieId);
    }

    @Override
    public void deleteMovie(int movieId) {
        jd.update("DELETE FROM MOVIELIST WHERE movieId = ?", movieId);
    }
}
