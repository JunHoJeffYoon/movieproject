package com.movie.booking;

import java.util.ArrayList;

import com.movie.movie.Movie;

public interface BookInterface {
	void printMovieList(ArrayList<Movie> MovieList); // 전체 영화리스트 출력

	boolean isBookInMovie(String name); // 예매수량 하나씩 증가

	void insertMovie(Movie p); // MovieItem에 영화 정보 등록

	void removeBook(int num); // 예매내역에서 순번 num의 항목을 삭제

	boolean isDeleteMovie(String name); // 장바구니의 예매수량 하나씩 감소

	void printBook(); // 예매리스트 출력

	public void printBill(); // 예매 영수증 출력

	

}
