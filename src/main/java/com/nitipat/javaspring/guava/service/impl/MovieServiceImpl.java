package com.nitipat.javaspring.guava.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitipat.javaspring.guava.configuration.MovieConfiguration;
import com.nitipat.javaspring.guava.domain.dto.MovieDto;
import com.nitipat.javaspring.guava.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	MovieConfiguration movieConfiguration;

	@Override
	public MovieDto getMovieById(long id) throws ExecutionException {
		Map<Long, MovieDto> movieMapId = new HashMap<Long, MovieDto>();
		movieMapId = movieConfiguration.getMoviesResponse("response");
		return movieMapId.get(id);
	}
	

}
