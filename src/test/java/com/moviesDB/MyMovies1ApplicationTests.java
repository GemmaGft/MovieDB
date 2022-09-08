package com.moviesDB;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.moviesDB.services.MovieService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
class MyMovies1ApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	MovieService movieService;

	@Test
	void getAllGenres() throws Exception {
		HashMap<String, Object> result = new HashMap<>();
		result.put("genres", 0);

		given(movieService.findAllGenres()).willReturn(result);
		ResultActions actions = mockMvc.perform(get("/api/genre/list"));

		actions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.genres", is(0)));

	}
	
	@Test
	void getAllPopularMovies() throws Exception {
		HashMap<String, Object> result = new HashMap<>();
		result.put("page", 0);

		given(movieService.findAllGenres()).willReturn(result);
		ResultActions actions = mockMvc.perform(get("/api/movie/popular"));

		actions.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.page", is(0)));
	}

}
