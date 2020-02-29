package com.nitipat.javaspring.guava.domain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
	private String title;
	private Long year;
	private List<String> cast;
	private List<String> genres;
}
