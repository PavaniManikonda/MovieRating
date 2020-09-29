package com.example.MovieRating;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("MovieRating")
public class MovieRatingController {

	@Autowired
	private RatingService ratingService;

	@Autowired
	private MovieService movieService;

	

	@RequestMapping("/api/movie/{movieId}/name/{name}")
	public void addMovies(@PathVariable String movieId, @PathVariable String name) {

		if ((movieId != null || !"".equalsIgnoreCase(movieId)) && (name != null || !"".equalsIgnoreCase(name))) {

		} else {
			System.out.println("Invalid data");
		}
		movieService.addMovie(new Movie(movieId, name));

	}

	@RequestMapping("/api/movie/{movieId}")
	public Movie getMovies(@PathVariable String movieId) {

		if ((movieId != null || !"".equalsIgnoreCase(movieId))) {

		} else {
			System.out.println("Invalid data");
		}
		return movieService.getMovie(movieId);

	}

	@RequestMapping("/api/customer/{customerId}/rate/{rating}")
	public Rating AddRatingDetails(@PathVariable String customerId, @PathVariable int rating) {
		Rating inRating;

		if ((customerId != null || !"".equalsIgnoreCase(customerId))) {

			RatingId ratingId = new RatingId("1214", customerId);

			inRating = new Rating(ratingId, rating,"abc");
			ratingService.addRating(inRating);

		} else {
			inRating = null;

			System.out.println("Invalid data");
		}

		return inRating;
	}

	
	@RequestMapping("/api/customer/{customerId}/rate/{rating}/movie/{movieId}")
	public Rating AddRatingDetailsWithMovieId(@PathVariable String customerId, @PathVariable int rating,@PathVariable String movieId) {
		Rating inRating;

		if ((customerId != null || !"".equalsIgnoreCase(customerId))) {

			RatingId ratingId = new RatingId(movieId, customerId);

			inRating = new Rating(ratingId, rating,"abc");
			ratingService.addRating(inRating);

		} else {
			inRating = null;

			System.out.println("Invalid data");
		}

		return inRating;
	}
	
	@RequestMapping("/api/HighestRatedMovie")
	public String getHigestRatedMovie() {

		Rating rating1 = new Rating(new RatingId("11", "1"), 3,"abc");
		Rating rating2 = new Rating(new RatingId("11", "2"), 2,"abc");
		Rating rating3 = new Rating(new RatingId("11", "3"), 4,"abc");

		Rating rating4 = new Rating(new RatingId("12", "1"), 3,"def");
		Rating rating5 = new Rating(new RatingId("12", "2"), 4,"def");
		Rating rating6 = new Rating(new RatingId("12", "3"), 5,"def");

		Rating rating7 = new Rating(new RatingId("13", "1"), 1,"ahg");
		Rating rating8 = new Rating(new RatingId("13", "2"), 4,"ahg");
		Rating rating9 = new Rating(new RatingId("13", "3"), 5,"ahg");

		ArrayList<Rating> ratingList = new ArrayList<Rating>();

		ratingList.add(rating1);
		ratingList.add(rating2);
		ratingList.add(rating3);
		ratingList.add(rating4);
		ratingList.add(rating5);
		ratingList.add(rating6);
		ratingList.add(rating7);
		ratingList.add(rating8);
		ratingList.add(rating9);
		
		

		List<String> distinctMovies = getMoviesList(ratingList);

		int highestRating = highestRatingBasedonAvg(ratingList, distinctMovies);

		return "Higest Rating for all movies based on individual movie Average is =" + highestRating;

	}
	
	
	
	@RequestMapping("/api/allMovieRatings")
	public List<Rating> fetchMoviesRatings() {

		
		List<Rating> ratingList=ratingService.findMovieRatings();
		ratingList.stream().forEach(System.out::println);
		return ratingList;
	}
	
	@RequestMapping("/api/AverageRating")
	public RatingResponse fetchAvgRating() {

		RatingResponse ratingResponse=new RatingResponse();
		List<Rating> ratingList=ratingService.findMovieRatings();
		ratingList.stream().forEach(System.out::println);
		IntSummaryStatistics avgRatStat = ratingList.stream().mapToInt((value) -> value.getRating()).summaryStatistics();
		
		ratingResponse.setAvgRating(avgRatStat.getAverage());
		int maxRating=avgRatStat.getMax();
		List<Rating> tmpList =ratingList.stream().filter(rating->maxRating==rating.getRating()).collect(Collectors.toList());
		String custIdReq=tmpList.get(0).getRatingId().getCustomerId();
		List<Rating> customerList =ratingList.stream().filter(rating->rating.getRatingId().getCustomerId().equals(custIdReq)).collect(Collectors.toList());
		
		IntSummaryStatistics custAvgRating = customerList.stream().mapToInt((value) -> value.getRating()).summaryStatistics();
		ratingResponse.setCusAvgRating(custAvgRating.getAverage());
		ratingResponse.setId(customerList.get(0).getRatingId().getCustomerId());
		ratingResponse.setName(customerList.get(0).getCusName());
		return ratingResponse;
	}
	
	

	// distinct Movies List
	List<String> getMoviesList(ArrayList<Rating> ratingList) {

		Iterator itr = ratingList.iterator();
		List<String> movieList = new ArrayList<String>();

		while (itr.hasNext()) {
			Rating r = (Rating) itr.next();

			if (!movieList.contains(r.getRatingId().getMovieId())) {
				movieList.add(r.getRatingId().getMovieId());
			}

		}

		return movieList;
	}

	int highestRatingBasedonAvg(ArrayList<Rating> ratingList, List<String> moviesList) {

		Iterator itr = moviesList.iterator();
		List<Integer> avgList = new ArrayList<Integer>();

		while (itr.hasNext()) {

			String movie = (String) itr.next();

			List<Rating> tmpList = ratingList.stream().filter(rating -> rating.getRatingId().getMovieId().equals(movie))
					.collect(Collectors.toList());

			IntSummaryStatistics tmpStat = tmpList.stream().mapToInt((value) -> value.getRating()).summaryStatistics();

			avgList.add(tmpStat.getMax());

		}

		return avgList.stream().mapToInt((x) -> x).summaryStatistics().getMax();
	}

}
