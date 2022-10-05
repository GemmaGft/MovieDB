package com.moviesDB.controllers;

import com.moviesDB.entities.UserMovie;
import com.moviesDB.repository.UserMovieRepository;
import com.moviesDB.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;

import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class MovieControllerTest {

    MovieController movieController;
    UserMovieRepository userMovieRepository;
    MovieService movieService;

    @BeforeEach
    void setUp(){
        movieService = mock(MovieService.class);
        userMovieRepository = mock(UserMovieRepository.class);
        movieController = new MovieController(movieService,userMovieRepository);
    }

    @Test
    void getConfiguration() {
        //GIVEN
        HashMap<String, Object> resultFromGetConfig = new HashMap<>();
        resultFromGetConfig.put("images",0);
        resultFromGetConfig.put("change_keys",0);

        //WHEN
        given(movieService.getConfig()).willReturn(resultFromGetConfig);

        //THEN
        assertEquals(resultFromGetConfig, movieController.getConfiguration());
    }

    @Test
    void findAllGenres(){
        //GIVEN
        HashMap<String, Object> resultFromGetGenres = new HashMap<>();
        resultFromGetGenres.put("images",0);
        resultFromGetGenres.put("change_keys",0);

        //WHEN
        given(movieService.findAllGenres()).willReturn(resultFromGetGenres);

        //THEN
        assertEquals(resultFromGetGenres, movieController.findAllGenres());
    }

    @Test
    void findAllPopularMovies() {
        //GIVEN
        HashMap<String, Object> resultFromGetPopularMovies = new HashMap<>();
        resultFromGetPopularMovies.put("images",0);
        resultFromGetPopularMovies.put("change_keys",0);

        //WHEN
        given(movieService.findPopularMovie()).willReturn(resultFromGetPopularMovies);

        //THEN
        assertEquals(resultFromGetPopularMovies, movieController.findAllPopularMovies());
    }

    @Test
    void findTopRated() {
        //GIVEN
        HashMap<String, Object> resultFromGetTopRatedMovies = new HashMap<>();
        resultFromGetTopRatedMovies.put("images",0);
        resultFromGetTopRatedMovies.put("change_keys",0);

        //WHEN
        given(movieService.findTopRated()).willReturn(resultFromGetTopRatedMovies);

        //THEN
        assertEquals(resultFromGetTopRatedMovies, movieController.findTopRated());
    }

    @Test
    @WithMockUser
    void findMovieByID() {
        //GIVEN
        HashMap<String, Object> resultFromGetMovieById = new HashMap<>();
        resultFromGetMovieById.put("images",0);
        resultFromGetMovieById.put("change_keys",0);
        Integer id = 550;
        UserDetails user = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return null;
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };

        //WHEN
        given(movieService.findMovieById(id)).willReturn(resultFromGetMovieById);

        //THEN
        assertEquals(resultFromGetMovieById, movieController.findMovieByID(user,id));
    }
    @Test
    @WithMockUser
    void findMovieByIDWhenUserExist() {
        //GIVEN
        HashMap<String, Object> resultFromGetMovieById = new HashMap<>();
        resultFromGetMovieById.put("images",0);
        resultFromGetMovieById.put("change_keys",0);
        UserMovie userMovie = new UserMovie();
        Integer id = 550;
        UserDetails user = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return null;
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };

        //WHEN
        given(movieService.findMovieById(id)).willReturn(resultFromGetMovieById);
        given(userMovieRepository.findByUsernameAndMovie(user.getUsername(),id.toString())).willReturn(Optional.of(userMovie));

        //THEN
        assertEquals(resultFromGetMovieById, movieController.findMovieByID(user,id));
    }


    @Test
    void findCredits() {
        //GIVEN
        HashMap<String, Object> resultFromGetCredits = new HashMap<>();
        resultFromGetCredits.put("images",0);
        resultFromGetCredits.put("change_keys",0);
        Integer id = 550;

        //WHEN
        given(movieService.findCredits(id)).willReturn(resultFromGetCredits);

        //THEN
        assertEquals(resultFromGetCredits, movieController.findCredits(id));
    }

    @Test
    void findImage() {
        //GIVEN
        HashMap<String, Object> resultFromGetImages = new HashMap<>();
        resultFromGetImages.put("images",0);
        resultFromGetImages.put("change_keys",0);
        Integer id = 550;

        //WHEN
        given(movieService.findImage(id)).willReturn(resultFromGetImages);

        //THEN
        assertEquals(resultFromGetImages, movieController.findImage(id));
    }

    @Test
    void findKeywords() {
        //GIVEN
        HashMap<String, Object> resultFromGetKeywords = new HashMap<>();
        resultFromGetKeywords.put("images",0);
        resultFromGetKeywords.put("change_keys",0);
        Integer id = 550;

        //WHEN
        given(movieService.findKeywords(id)).willReturn(resultFromGetKeywords);

        //THEN
        assertEquals(resultFromGetKeywords, movieController.findKeywords(id));
    }

    @Test
    void findRecommendation() {
        //GIVEN
        HashMap<String, Object> resultFromGetRecommendation = new HashMap<>();
        resultFromGetRecommendation.put("images",0);
        resultFromGetRecommendation.put("change_keys",0);
        Integer id = 550;

        //WHEN
        given(movieService.findRecommendation(id)).willReturn(resultFromGetRecommendation);

        //THEN
        assertEquals(resultFromGetRecommendation, movieController.findRecommendation(id));
    }

    @Test
    void findSimilar() {
        //GIVEN
        HashMap<String, Object> resultFromGetSimilar = new HashMap<>();
        resultFromGetSimilar.put("images",0);
        resultFromGetSimilar.put("change_keys",0);
        Integer id = 550;

        //WHEN
        given(movieService.findSimilar(id)).willReturn(resultFromGetSimilar);

        //THEN
        assertEquals(resultFromGetSimilar, movieController.findSimilar(id));
    }

    @Test
    void setNewInformationIntoUser() {
        //GIVEN
        Integer id = 550;
        UserMovie userMovie = new UserMovie();
        userMovie.setMovie(id.toString());
        UserDetails user = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return null;
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };
        ResponseEntity<UserMovie> responseEntity = new ResponseEntity<UserMovie>(userMovie, HttpStatus.OK);

        //WHEN
        given(userMovieRepository.findByUsernameAndMovie(user.getUsername(), id.toString())).willReturn(Optional.of(userMovie));
        given(userMovieRepository.save(userMovie)).willAnswer((invocation ->  invocation.getArgument(0)));

        //THEN
        assertEquals(responseEntity, movieController.patchUserMovie(id, userMovie, user));
    }
    @Test
    void setNewInformationIntoUserIfUserMovieNotExist() {
        //GIVEN
        Integer id = 550;
        UserMovie userMovie = null;
        UserMovie userMovieBodyRequest = new UserMovie();
        userMovieBodyRequest.setMovie(id.toString());
        UserDetails user = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return null;
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };
        ResponseEntity<UserMovie> responseEntity = new ResponseEntity<UserMovie>(userMovieBodyRequest, HttpStatus.OK);

        //WHEN
        given(userMovieRepository.findByUsernameAndMovie(user.getUsername(), id.toString())).willReturn(Optional.ofNullable(userMovie));
        given(userMovieRepository.save(userMovieBodyRequest)).willAnswer((invocation ->  invocation.getArgument(0)));

        //THEN
        assertEquals(responseEntity.toString(), movieController.patchUserMovie(id, userMovieBodyRequest, user).toString());
    }
}