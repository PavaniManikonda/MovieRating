package com.example.MovieRating;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rating {
	
	
	
	@Id
	private RatingId ratingId;
	
	private int rating;
	
	private String cusName;
	
	public Rating() {
		
	}
	
	
	

	public Rating(RatingId ratingId, int rating, String cusName) {
		super();
		this.ratingId = ratingId;
		this.rating = rating;
		this.cusName = cusName;
	}




	public RatingId getRatingId() {
		return ratingId;
	}

	public void setRatingId(RatingId ratingId) {
		this.ratingId = ratingId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	

	


	



	
	
	

	

	

	
	
}
