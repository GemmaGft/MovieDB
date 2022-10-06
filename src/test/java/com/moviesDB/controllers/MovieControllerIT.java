package com.moviesDB.Integration;

import com.moviesDB.controllers.MovieController;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {JdbcDataSource.class})
@WebAppConfiguration(value = "src/main/java/com/moviesDB/MyMovies1Application.java")
@AutoConfigureMockMvc(addFilters = false)
@EnableWebMvc
public class MovieControllerIT {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired(required = true)
    private MockMvc mockMvc;

    @MockBean
    MovieController movieController;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesMovieController() {
        ServletContext servletContext = webApplicationContext.getServletContext();
        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean(MovieController.class));
    }

    @Test
    void GIVEN_apiConfiguration_WHEN_MockMvc_THEN_VerifyResponse() throws Exception {
        //GIVEN
        ResultActions request = mockMvc.perform(get("/api/configuration"));
        //WHEN
        MvcResult mvcResult = request.andDo(print()).andExpect(status().isOk()).andReturn();
        //THEN
        assertEquals("application/json", mvcResult.getResponse().getContentType());
    }

    @Test
    void GIVEN_apiGenreList_WHEN_MockMvc_THEN_VerifyResponse() throws Exception {
        //GIVEN
        ResultActions request = mockMvc.perform(get("/api/genre/list"));
        //WHEN
        MvcResult mvcResult = request.andDo(print()).andExpect(status().isOk()).andReturn();
        //THEN
        assertEquals("application/json", mvcResult.getResponse().getContentType());
    }

    @Test
    void GIVEN_apiPopular_WHEN_MockMvc_THEN_VerifyResponse() throws Exception {
        //GIVEN
        ResultActions request = mockMvc.perform(get("/api/movie/popular"));
        //WHEN
        MvcResult mvcResult = request.andDo(print()).andExpect(status().isOk()).andReturn();
        //THEN
        assertEquals("application/json", mvcResult.getResponse().getContentType());
    }

    @ParameterizedTest
    @CsvSource(value = {"/api/movie/popular", "/api/genre/list", "/api/configuration"})
    void GIVEN_apiURL_WHEN_MockMvc_THEN_VerifyResponse(String uri) throws Exception {
        //GIVEN
        ResultActions request = mockMvc.perform(get(uri));
        //WHEN
        MvcResult mvcResult = request.andDo(print()).andExpect(status().isOk()).andReturn();
        //THEN
        assertEquals("application/json", mvcResult.getResponse().getContentType());
    }
}