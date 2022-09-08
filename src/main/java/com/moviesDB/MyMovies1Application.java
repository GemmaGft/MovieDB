package com.moviesDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableCaching
@Configuration
public class MyMovies1Application {

	public static void main(String[] args) {
		SpringApplication.run(MyMovies1Application.class, args);
	}
}
