package com.movie.booking;

import com.movie.movie.Movie;

public class BookItem {

	private int total; // 예매장수 총함
	private int price; // 예매 가격
	private String movieName; // 영화제목
	private String date; // 상영날짜
	private String roomNum; // 상영관
	private Movie itemMovie; // movie 클래스
	private String movieId; // 영화 ID
	private int quantity; // 영화 수량
	private int totalPrice; // 예매 합계 가격
//	public BookItem() {
//	}

	public BookItem(Movie itemMovie) {
		super();
		this.total = itemMovie.getTotal();
		this.price = itemMovie.getPrice();
		this.movieName = itemMovie.getName();
		this.date = itemMovie.getDate();
		this.roomNum = itemMovie.getRoomNum();
		this.itemMovie = itemMovie;
		this.movieId = itemMovie.getMovieId();
		this.quantity = itemMovie.getQuantity();
		updateTotalprice();
	}

	public Movie getItemMovie() {
		return itemMovie;
	}

	public void setItemMovie(Movie itemMovie) {
		this.itemMovie = itemMovie;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
		this.updateTotalprice();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.updateTotalprice();
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	private void updateTotalprice() {
		totalPrice = this.itemMovie.getPrice() * this.quantity;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int quantity, int num) {
		this.total = quantity + num;
	}

}
