package com.callor.movie.service;

import com.callor.movie.model.NaverMovieDTO;

public interface NaverMovieService {
    public NaverMovieDTO getMovies(String search);
}
