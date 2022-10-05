package com.moviesDB.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviesDB.Utils.JSONResponses;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieServiceTest {
    MovieService movieService;
    ObjectMapper objectMapper;
    public MockWebServer mockBackEnd;
    JSONResponses jsonResponses;
    HashMap<String, Object> expectedResponse;

    @AfterEach
    void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    void initialize() throws IOException {
        mockBackEnd = new MockWebServer();
        objectMapper = new ObjectMapper();
        jsonResponses = new JSONResponses();
        movieService = new MovieService(mockBackEnd.url("/").url().toString());
        expectedResponse = new HashMap<>();
        expectedResponse.put("images", 0);
        expectedResponse.put("change_keys", 0);

    }

    @Test
    void getConfig() throws IOException, InterruptedException {
        //Given
        mockBackEnd.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(objectMapper.writeValueAsString(expectedResponse)));

        //When
        HashMap<String, Object> actual = movieService.getConfig();

        //Then
        assertEquals(expectedResponse.toString(), actual.toString());
    }

    @Test
    void findAllGenres() throws JsonProcessingException {
        //Given
        mockBackEnd.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(objectMapper.writeValueAsString(expectedResponse)));

        //When
        HashMap<String, Object> actual = movieService.findAllGenres();

        //Then
        assertEquals(expectedResponse.toString(), actual.toString());
    }

    @Test
    void findAllPopularMovies() throws JsonProcessingException {
        //Given
        mockBackEnd.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(objectMapper.writeValueAsString(expectedResponse)));

        //When
        HashMap<String, Object> actual = movieService.findPopularMovie();

        //Then
        assertEquals(expectedResponse.toString(), actual.toString());
    }

    @Test
    void findTopRatedMovies() throws JsonProcessingException {
        //Given
        mockBackEnd.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(objectMapper.writeValueAsString(expectedResponse)));

        //When
        HashMap<String, Object> actual = movieService.findTopRated();

        //Then
        assertEquals(expectedResponse.toString(), actual.toString());
    }

    @Test
    void findMovieById() throws JsonProcessingException {
        //Given
        Integer id = 420;
        mockBackEnd.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(objectMapper.writeValueAsString(expectedResponse)));

        //When
        HashMap<String, Object> actual = movieService.findMovieById(id);

        //Then
        assertEquals(expectedResponse.toString(), actual.toString());
    }

    @Test
    void findCreditsById() throws JsonProcessingException {
        //Given
        Integer id = 420;
        mockBackEnd.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(objectMapper.writeValueAsString(expectedResponse)));

        //When
        HashMap<String, Object> actual = movieService.findCredits(id);

        //Then
        assertEquals(expectedResponse.toString(), actual.toString());
    }

    @Test
    void findImagesById() throws JsonProcessingException {
        //Given
        Integer id = 420;
        mockBackEnd.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(objectMapper.writeValueAsString(expectedResponse)));

        //When
        HashMap<String, Object> actual = movieService.findImage(id);

        //Then
        assertEquals(expectedResponse.toString(), actual.toString());
    }

    @Test
    void findKeywordsById() throws JsonProcessingException {
        //Given
        Integer id = 420;
        mockBackEnd.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(objectMapper.writeValueAsString(expectedResponse)));

        //When
        HashMap<String, Object> actual = movieService.findKeywords(id);

        //Then
        assertEquals(expectedResponse.toString(), actual.toString());
    }

    @Test
    void findRecommendationsById() throws JsonProcessingException {
        //Given
        Integer id = 420;
        mockBackEnd.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(objectMapper.writeValueAsString(expectedResponse)));

        //When
        HashMap<String, Object> actual = movieService.findRecommendation(id);

        //Then
        assertEquals(expectedResponse.toString(), actual.toString());
    }

    @Test
    void findSimilarMoviesById() throws JsonProcessingException {
        //Given
        Integer id = 420;
        mockBackEnd.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(objectMapper.writeValueAsString(expectedResponse)));

        //When
        HashMap<String, Object> actual = movieService.findSimilar(id);

        //Then
        assertEquals(expectedResponse.toString(), actual.toString());
    }
}