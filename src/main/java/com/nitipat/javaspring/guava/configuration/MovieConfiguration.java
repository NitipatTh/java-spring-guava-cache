package com.nitipat.javaspring.guava.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.nitipat.javaspring.guava.domain.dto.MovieDto;
import com.nitipat.javaspring.guava.domain.response.MoviesResponse;

@Configuration
public class MovieConfiguration {
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static final String MOVIE_DATA_URL = "https://raw.githubusercontent.com/prust/wikipedia-movie-data/master/movies.json";
	
	private LoadingCache<String, Map<Long, MovieDto>> loadingCache = CacheBuilder.newBuilder()
			.refreshAfterWrite(60, TimeUnit.SECONDS)
			.recordStats().maximumSize(10)
			.build(new CacheLoader<String,Map<Long, MovieDto>>() {

			@Override
			public Map<Long, MovieDto> load(String key) throws Exception {
				System.out.println("Download from URL");
				RestTemplate restTemplate = new RestTemplate();
				String jsonString = restTemplate.getForObject(MOVIE_DATA_URL, String.class);
				Map<Long, MovieDto> movieMapId = mapIdtoMovieList(jsonString);
			    loadingCache.put("response", movieMapId);
			    System.out.println("CacheSize: " + loadingCache.size());
 				return loadingCache.get("response");
			}
	});
	
	private Map<Long, MovieDto> mapIdtoMovieList(String jsonString) {
		
		MoviesResponse moviesResponse = new MoviesResponse();
		try {
			moviesResponse = new ObjectMapper().readValue(jsonString, MoviesResponse.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<Long, MovieDto> movieMapId = new HashMap<Long, MovieDto>();
		Long index = 1L;
		for (MovieDto movie : moviesResponse) {
			movieMapId.put(index, movie);
		}
		return movieMapId;
	}
	
	public Map<Long, MovieDto> getMoviesResponse(String key) throws ExecutionException {
		return loadingCache.get(key);
	}
	
	public CacheStats getCacheStats() {
		return loadingCache.stats();	
	}
}
