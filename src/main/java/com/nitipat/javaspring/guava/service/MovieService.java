package com.nitipat.javaspring.guava.service;

import java.util.concurrent.ExecutionException;

import com.nitipat.javaspring.guava.domain.dto.MovieDto;

public interface MovieService {

	MovieDto getMovieById(long id) throws ExecutionException; 

}
