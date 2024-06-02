package com.movie.movie;

public class Movie extends Item {
	private int total; 			//예매 장수 총합
	private int quantity;		//예매 수량
	private int price; 			//예매 가격
	private String director;	//감독
	private String genre;		//장르
	private String releaseDate;	//개봉일
	private String roomNum;		//관번호
	private String date;		//날짜 및 시간
//	private String sitNum;		//좌석번호
	public Movie() {
		
	}
	public Movie(String movieId, String name) {
		super(movieId, name);
	}
	
	public Movie(String movieId, String name, String date, String director, String genre, String releaseDate, String roomNum) {
		super(movieId, name);
		this.director = director;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.roomNum = roomNum;
		this.date = date;
//		this.sitNum = sitNum;
	}
	@Override
	public String getMovieId() {
		return movieId;
	}
	@Override
	public void setMovieId(String movieId) {
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
//	public String getSitNum() {
//		return sitNum;
//	}
//	public void setSitNum(String sitNum) {
//		this.sitNum = sitNum;
//	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int quanitity, int num) {
		this.total = quantity + num;
	}
	
	
	
}
