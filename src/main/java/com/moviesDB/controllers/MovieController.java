package com.moviesDB.controllers;

import com.moviesDB.entities.UserMovie;
import com.moviesDB.repository.UserMovieRepository;
import com.moviesDB.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class MovieController {

    private final MovieService movieService;
    private final UserMovieRepository userMovieRepository;

    public MovieController(MovieService movieService, UserMovieRepository userMovieRepository) {
        this.movieService = movieService;
        this.userMovieRepository = userMovieRepository;
    }

    @GetMapping("/api/configuration")
    public HashMap<String, Object> getConfiguration() {
        return movieService.getConfig();
    }

    @GetMapping("/api/genre/list")
    public HashMap<String, Object> findAllGenres(){
        return movieService.findAllGenres();
    }

    @GetMapping("/api/movie/popular")
    public HashMap<String, Object> findAllPopularMovies() {
        return movieService.findPopularMovie();
    }

    @GetMapping("/api/movie/topRated")
    public HashMap<String, Object> findTopRated()  {
        return movieService.findTopRated();
    }

    @GetMapping("/api/movie/{id}")
    public Object findMovieByID(@AuthenticationPrincipal UserDetails user, @PathVariable int id) {
        String movieid = Integer.toString(id);
        UserMovie userMovie = userMovieRepository.findByUsernameAndMovie(user.getUsername(), movieid).orElse(null);

        HashMap<String, Object> movie = movieService.findMovieById(id);
        if (userMovie != null) {
            movie.put("favorite", userMovie.getFavorite());
            movie.put("personal_rating", userMovie.getPersonal_rating());
            movie.put("notes", userMovie.getNotes());
        }
        return movie;
    }

    @GetMapping("/api/movie/{id}/credit")
    public HashMap<String, Object> findCredits(@PathVariable int id) {
        return movieService.findCredits(id);
    }

    @GetMapping("/api/movie/{id}/images")
    public HashMap<String, Object> findImage(@PathVariable int id)  {
        return movieService.findImage(id);
    }

    @GetMapping("/api/movie/{id}/keywords")
    public HashMap<String, Object> findKeywords(@PathVariable int id) {
        return movieService.findKeywords(id);
    }

    @GetMapping("/api/movie/{id}/recommendations")
    public HashMap<String, Object> findRecommendation(@PathVariable int id)  {
        return movieService.findRecommendation(id);
    }

    @GetMapping("/api/movie/{id}/similar")
    public HashMap<String, Object> findSimilar(@PathVariable int id)  {
        return movieService.findSimilar(id);
    }

    @PatchMapping("/api/movie/{id}")
    public ResponseEntity<UserMovie> patchUserMovie(@PathVariable int id, @RequestBody UserMovie userMovie,
                                                    @AuthenticationPrincipal UserDetails user) {
        String movieid = Integer.toString(id);
        UserMovie updatedMovie = userMovieRepository.findByUsernameAndMovie(user.getUsername(), movieid).orElse(null);
        if (updatedMovie == null) {
            updatedMovie = new UserMovie();
        }
        updatedMovie.setUsername(user.getUsername());
        updatedMovie.setMovie(movieid);
        updatedMovie.setFavorite(userMovie.getFavorite());
        updatedMovie.setPersonal_rating(userMovie.getPersonal_rating());
        updatedMovie.setNotes((userMovie.getNotes()));

        userMovieRepository.save(updatedMovie);

        return new ResponseEntity<UserMovie>(updatedMovie, HttpStatus.OK);
    }

}
