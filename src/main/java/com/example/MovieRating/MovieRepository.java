package com.example.MovieRating;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface MovieRepository extends CrudRepository<Movie,String>{

	List<Movie> findByMovieId(String customerId);
}
