package com.example.MovieRating;

public class RatingResponse {

	private String id;
	private String name;
	private double cusAvgRating;
	private double avgRating;
	public RatingResponse(String id, String name, double cusAvgRating, double avgRating) {
		super();
		this.id = id;
		this.name = name;
		this.cusAvgRating = cusAvgRating;
		this.avgRating = avgRating;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCusAvgRating() {
		return cusAvgRating;
	}
	public void setCusAvgRating(double cusAvgRating) {
		this.cusAvgRating = cusAvgRating;
	}
	public double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
	public RatingResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
}
