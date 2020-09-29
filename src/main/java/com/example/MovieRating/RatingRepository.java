package com.example.MovieRating;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface RatingRepository extends CrudRepository<Rating,RatingId>{

	
}
