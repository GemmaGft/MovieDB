package com.moviesDB.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@SuppressWarnings("unchecked")
public class MovieService {

	WebClient webClient;

	private String api_key ="2b868b906423d3e47dcc13efbc5a14e1";

	String baseUrl;
	public MovieService(){
		this.baseUrl = "https://api.themoviedb.org/3/";
		webClient = WebClient.create(baseUrl);
	}
	public MovieService(String baseUrl) {
		this.baseUrl = baseUrl;
		webClient = WebClient.create(baseUrl);
	}

	@Cacheable("configuration")
	public HashMap<String, Object> getConfig() {
		System.out.println(api_key);
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.path("configuration").queryParam("api_key", api_key).build()).retrieve()
				.bodyToMono(HashMap.class).block();
	}

	@Cacheable(value = "genres")
	public HashMap<String, Object> findAllGenres() {
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.path("genre/movie/list").queryParam("api_key", api_key).build())
				.retrieve().bodyToMono(HashMap.class).block();
	}

	@Cacheable(value = "popular")
	public HashMap<String, Object> findPopularMovie() {
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.path("movie/popular").queryParam("api_key", api_key).build()).retrieve()
				.bodyToMono(HashMap.class).block();
	}

	@Cacheable(value = "movie")
	public HashMap<String, Object> findMovieById(int id) {
		return webClient.get().uri(uriBuilder -> uriBuilder.path("movie/" + id).queryParam("api_key", api_key).build())
				.retrieve().bodyToMono(HashMap.class).block();
	}

	@Cacheable(value = "top_rated")
	public HashMap<String, Object> findTopRated() {
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.path("movie/top_rated").queryParam("api_key", api_key).build()).retrieve()
				.bodyToMono(HashMap.class).block();
	}

	@Cacheable(value = "credits")
	public HashMap<String, Object> findCredits(int id) {
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.path("movie/" + id + "/credits").queryParam("api_key", api_key).build())
				.retrieve().bodyToMono(HashMap.class).block();
	}

	@Cacheable(value = "image")
	public HashMap<String, Object> findImage(int id) {
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.path("movie/" + id + "/images").queryParam("api_key", api_key).build())
				.retrieve().bodyToMono(HashMap.class).block();
	}

	@Cacheable(value = "keywords")
	public HashMap<String, Object> findKeywords(int id) {
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.path("movie/" + id + "/keywords").queryParam("api_key", api_key).build())
				.retrieve().bodyToMono(HashMap.class).block();
	}

	@Cacheable(value = "recommendation")
	public HashMap<String, Object> findRecommendation(int id) {
		return webClient.get().uri(uriBuilder -> uriBuilder.path("movie/" + id + "/recommendations")
				.queryParam("api_key", api_key).build()).retrieve().bodyToMono(HashMap.class).block();
	}

	@Cacheable(value = "similar")
	public HashMap<String, Object> findSimilar(int id) {
		return webClient.get()
				.uri(uriBuilder -> uriBuilder.path("movie/" + id + "/similar").queryParam("api_key", api_key).build())
				.retrieve().bodyToMono(HashMap.class).block();
	}

}
