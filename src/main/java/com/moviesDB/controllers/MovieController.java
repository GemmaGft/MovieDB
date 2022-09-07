package com.moviesDB.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.moviesDB.services.MovieService;

@RestController
public class MovieController {

	 @Autowired
	    MovieService movieService;
	    
	    @GetMapping("/api/genre/list")
		public HashMap<String,Object> findAllGenres() throws IOException{
			return movieService.findAllGenres();
		}
	    
	    @GetMapping("/api/movie/popular")
		public  HashMap<String,Object> findAllPopularMovies() throws IOException{
			return movieService.findPopularMovie();
		}
	    
	    @GetMapping("/api/movie/topRated")
		public  HashMap<String,Object> findTopRated() throws IOException{
			return movieService.findTopRated();
		}
	    
	    @GetMapping("/api/movie/{id}")
		public  HashMap<String,Object>  findMovieByID(@PathVariable int id) throws IOException{
			return movieService.findMovieById(id);
		}
	    
	    @GetMapping("/api/movie/{id}/credit")
		public HashMap<String,Object> findCredits(@PathVariable int id) throws IOException{
			return movieService.findCredits(id);
		}
		
		@GetMapping("/api/movie/{id}/images")
		public HashMap<String,Object>findImage(@PathVariable int id) throws IOException{
			return movieService.findImage(id);
		}
		
		@GetMapping("/api/movie/{id}/keywords")
		public HashMap<String,Object> findKeywords(@PathVariable int id) throws IOException{
			return movieService.findKeywords(id);
		}
		@GetMapping("/api/movie/{id}/recommendations")
		public HashMap<String,Object> findRecommendation(@PathVariable int id) throws IOException{
			return movieService.findRecommendation(id);
		}
		
		
		@GetMapping("/api/movie/{id}/similar")
		public HashMap<String,Object> findSimilar(@PathVariable int id) throws IOException{
			return movieService.findSimilar(id);
		}
}
