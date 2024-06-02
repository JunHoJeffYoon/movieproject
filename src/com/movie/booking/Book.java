package com.movie.booking;

import java.util.ArrayList;
import java.util.Scanner;

import com.movie.movie.Movie;

public class Book implements BookInterface {
	public static Scanner sc = new Scanner(System.in);
	public ArrayList<BookItem> bookItem = new ArrayList<BookItem>();
	public static final int ADULT = 10000; // 성인요금
	public static final int KID = 6000; // 청소년 요금
	public static int bookcount = 0;

	public Book() {

	}

	// 전체 영화 리스트 출력
	@Override
	public void printMovieList(ArrayList<Movie> movieList) {
		System.out.println("========================================================================================");
		System.out.println("영화번호 \t\t  | 상영시간\t\t |"+ "영화제목 \t\t |  장르 \t |  감독 \t |  관번호 \t |  개봉일");
		for (int i = 0; i < movieList.size(); i++) {
			Movie movie = movieList.get(i);
			System.out.print(movie.getMovieId() + " | ");
			System.out.print(movie.getDate() + "\t | ");
			System.out.print(movie.getName() + "\t\t | ");
			System.out.print(movie.getGenre() + "\t | ");
			System.out.print(movie.getDirector() + "\t | ");
			System.out.print(movie.getRoomNum() + "\t | ");
//			System.out.print(movie.getSitNum());
			System.out.println(movie.getReleaseDate());
			System.out.print("");
		}
		System.out.println("========================================================================================");
	}

	// 예매수량 선택한만큼 증가
	@Override
	public boolean isBookInMovie(String name) {
		boolean flag = false;

		for (int i = 0; i < bookItem.size(); i++) {
			if (name.equals(bookItem.get(i).getMovieId())) {
				bookItem.get(i).setQuantity(bookItem.get(i).getQuantity() + 1);
				flag = true;
			}
		}
		return flag;
	}

	// MovieItem에 영화 정보 등록
	@Override
	public void insertMovie(Movie movie) {
		BookItem movieItem = new BookItem(movie);
		bookItem.add(movieItem);
		bookcount = bookItem.size();
	}

	@Override
	// 예매리스트에서 MovieName 항목을 삭제
	public void removeBook(int numId) {
		bookItem.remove(numId);
		bookcount = bookItem.size();

	}

	// 예매리스트 수량 감소
	@Override
	public boolean isDeleteMovie(String name) {
		boolean flag = false;
		for (int i = 0; i < bookItem.size(); i++) {
			if (name.equals(bookItem.get(i).getMovieName())) {
				bookItem.get(i).setTotal(bookItem.get(i).getTotal(), -1);
				flag = true;
			}
		}
		return flag;
	}

	// 예매리스트 출력
	public void printBook() {
		System.out.println("예매 상품 목록보기 : ");
		System.out.println("----------------------------------------------");
		System.out.println("    영화제목\t|     수량\t|      합계");

		for (int i = 0; i < bookItem.size(); i++) {
			System.out.print("    " + bookItem.get(i).getMovieName() + "\t| ");
			System.out.print("    " + bookItem.get(i).getQuantity() + " \t ");
			System.out.print("    " + bookItem.get(i).getTotalPrice() + "\t");
			System.out.println("  ");
		}

	}
	//예매 영수증 출력
	@Override
	public void printBill() {
		System.out.println();
		System.out.println("====================예매 내역===================");
		for(int i=0;i<bookItem.size();i++) {
			System.out.println("상영날짜 : "+ bookItem.get(i).getDate() + "\n영화제목: " +bookItem.get(i).getMovieName());
			System.out.println("가격 ("+ bookItem.get(i).getTotal()+"매): " + bookItem.get(i).getPrice()*bookItem.get(i).getTotal()+"원\n좌석번호 : ");
			System.out.println("관번호 : "+ bookItem.get(i).getRoomNum());
		}

	}
}
