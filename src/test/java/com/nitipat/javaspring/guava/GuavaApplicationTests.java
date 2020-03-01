package com.nitipat.javaspring.guava;

import java.util.concurrent.ExecutionException;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nitipat.javaspring.guava.configuration.MovieConfiguration;
import com.nitipat.javaspring.guava.domain.dto.MovieDto;
import com.nitipat.javaspring.guava.service.MovieService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class GuavaApplicationTests {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private MovieConfiguration movieConfiguration;
	
	@Test
	public void testHitCache() throws ExecutionException {
		// try to get data first time 
		Assert.assertThat(movieConfiguration.getCacheStats().hitCount(), Matchers.equalTo(0L));
		MovieDto result = movieService.getMovieById(1L);
		Assert.assertEquals(result.getTitle(), "Destroyer");
		Assert.assertThat(result.getYear(),  Matchers.equalTo(2018L));
		
		// hit cache
		Assert.assertThat(movieConfiguration.getCacheStats().hitCount(), Matchers.equalTo(1L));
		MovieDto resultFromCache = movieService.getMovieById(1L);
		Assert.assertEquals(resultFromCache.getTitle(), "Destroyer");
		Assert.assertThat(resultFromCache.getYear(),  Matchers.equalTo(2018L));
		
	}

}
