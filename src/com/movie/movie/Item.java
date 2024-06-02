package com.movie.movie;

public abstract class Item {
	String movieId;	//영화ID
	String name;	//영화명
	
	public Item() {
		
	}
	public Item(String movieId, String name) {
		super();
		this.movieId = movieId;
		this.name = name;
		
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
