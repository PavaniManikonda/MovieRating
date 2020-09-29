package com.example.MovieRating;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;



@Repository
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	public List<Rating> findMovieRatings(){
		
		List<Rating> movieRatingList=new ArrayList<Rating>();
		
		ratingRepository.findAll().forEach(movieRatingList::add);
		
		return movieRatingList;
		
	}
	
	public void addRating(Rating rating) {

		ratingRepository.save(rating);

	}
	
	/*
	 * public List<Rating> findHighestRatedMovie(){ List<Rating> movieRatingList=new
	 * ArrayList<Rating>();
	 * 
	 * ratingRepository.findAll().forEach(movieRatingList::add);
	 * 
	 * List<Rating> movieList = movieRatingList.stream() .filter(distinctByKey(p ->
	 * p.getMovieId())) .collect(Collectors.toList());
	 * 
	 * 
	 * return null;
	 * 
	 * 
	 * 
	 * }
	 */
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) 
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
