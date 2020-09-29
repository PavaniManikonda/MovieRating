package com.example.MovieRating;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	
	public List<Movie> getMovies(){
		List<Movie> movies=new ArrayList<Movie>();
		 movieRepository.findAll().forEach(movies :: add);
		return movies;
	}
	
	
	public Movie getMovie(String id) {
		
		return movieRepository.findById(id).get();
	}

	
	public void addMovie(Movie m) {
		
		movieRepository.save(m);
		
	}
	
}
