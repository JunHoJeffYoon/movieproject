package com.movie.moviemain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.movie.booking.Book;
import com.movie.exception.BookException;
import com.movie.member.Member;
import com.movie.movie.Movie;

public class MovieMain {
	static Book book = new Book();
	static Member member; // 사용자

	public static Scanner sc = new Scanner(System.in);
	public static final int ADULT = 10000; // 성인요금
	public static final int KID = 6000; // 청소년요금

	public static void main(String[] args) throws BookException {
		Scanner input = new Scanner(System.in);
		String userName = null; // 고객이름
		int userPhone = 0; // 연락처
		int numSelection = 0; // 메뉴번호선택

		ArrayList<Movie> movieInfoList = null; // 영화 리스트
		int totalMovieCount = 0; // 영화 총개수 저장

		System.out.println("고객 정보 입력");
		System.out.print("이름을 입력해주세요 : ");
		userName = input.next();

		System.out.print("연락처를 입력하세요 : ");
		userPhone = input.nextInt();

		// 사용자 정보 저장
		member = new Member(userName, userPhone);

		boolean quit = false; // 무한 반복

		while (!quit) {
			menuIntroduction();

			try {
				System.out.println("메뉴 번호를 선택해주세요 : ");
				numSelection = input.nextInt();

				if (numSelection < 1 || numSelection > 8) {
					System.out.println("원하시는 메뉴 숫자를 입력하세요. ");
				} else {
					switch (numSelection) {
					case 1:
						guestInfo(userName, userPhone);
						break;
					case 2:
						// 영화 목록 보기
						movieInfoList = new ArrayList<Movie>();
						movieBooking(movieInfoList);
						break;
					case 3:
						// 예매리스트 확인
						movieBookList();
						break;

					case 4:
						// 예매 항목 수량 줄이기
						bookRemoveCount(movieInfoList);
						break;

					case 5:
						// 예매 항복 삭제
						bookRemoveItem();
						break;

					case 6:
						// 6.영수증 표시
						bookMoviekBill();
						break;

					case 7:
						// 8.종료
						menuExit();
						quit = true;
						break;
					case 9:// 7.관리자 로그인 정보 확인 메서드 호출
						menuAdminLogin();
						break;

					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("잘못된 메뉴 선택으로 종료합니다.");
				quit = true;
			}

		}

	}

	private static void menuIntroduction() {
		System.out.println("**********************************************");
		System.out.println("\t\t" + "Movie Menu");
		System.out.println("**********************************************");
		System.out.println(" 1. 예매자 정보 확인하기 \t5. 예매 리스트 비우기");
		System.out.println(" 2. 영화 예매 \t\t6. 예매 영수증 출력");
		System.out.println(" 3. 예매리스트 보기    \t7. 관리자 로그인");
		System.out.println(" 4. 예매 수량 줄이기   \t8. 종료");
		System.out.println("**********************************************");

	}

	// 고객 정보 확인
	private static void guestInfo(String userName, int userMobile) {
		System.out.println("현재 고객 정보");
		System.out.println("이름 : " + member.getName() + "연락쳐: " + member.getPhone());

	}

	// 예매 메서드
	private static void movieBooking(ArrayList<Movie> movie) {
		movieList(movie);

		book.printMovieList(movie);
		boolean quit = false;

		while (!quit) {
			System.out.print("예매할 영화 제목을 입력하세요: ");
			String inputStr = sc.next();

			boolean flag = false;
			int numId = -1;
			for (int i = 0; i < movie.size(); i++) {
				if (inputStr.equals(movie.get(i).getName())) {
					numId = i;
					flag = true;
					break;
				}
			}
			System.out.print("10000원\t|6000원\n성인\t|청소년 :");
			String age = sc.next();
			if (age.equals("성인")) {
				movie.get(numId).setPrice(ADULT);
			} else if (age.equals("청소년")) {
				movie.get(numId).setPrice(KID);
			} else {
				System.out.println("제대로 입력해주세요");
			}
			// 영화 제목 일치 여부 및 성인 청소년 확인 후 예매 여부를 묻기.
			if (flag) {
				// 수량 복수 입력
				addNum(movie);
				System.out.println("예매 하시겠습니까? Y|N");
				inputStr = sc.next();

				if (inputStr.toUpperCase().equals("Y") || inputStr.toUpperCase().equals("y")) {
					System.out.println(
							movie.get(numId).getName() + " " + movie.get(numId).getTotal() + "장이 예매리스트에 추가되었습니다.");
					book.insertMovie(movie.get(numId));

					quit = true;
				} else {
					System.out.println("다시 입력해주세요.");
				}

			}
		}

	}

	// 수량 복수 입력
	private static void addNum(ArrayList<Movie> movie) {
		System.out.print("수량을 입력해주세요: ");
		int num = sc.nextInt();
		for (int i = 0; i < movie.size(); i++) {
			movie.get(i).setTotal(movie.get(i).getQuantity(), num);
		}
	}

	// 영화리스트 불러오는 메서드
	private static void movieList(ArrayList<Movie> movie) {
		setFileToMovieList(movie);
	}

	private static void setFileToMovieList(ArrayList<Movie> movie) {
		try {
			FileReader fr = new FileReader("movie.txt");
			BufferedReader br = new BufferedReader(fr);

			String movieId;
			String[] readMovie = new String[7];

			while ((movieId = br.readLine()) != null) {
				if (movieId.contains("movie")) {
					readMovie[0] = movieId;
					readMovie[1] = br.readLine();
					readMovie[2] = br.readLine();
					readMovie[3] = br.readLine();
					readMovie[4] = br.readLine();
					readMovie[5] = br.readLine();
					readMovie[6] = br.readLine();
				}
				Movie movieList = new Movie(readMovie[0], readMovie[1], readMovie[2], readMovie[3], readMovie[4],
						readMovie[5], readMovie[6]);
				movie.add(movieList);
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 예매리스트 확인
	private static void movieBookList() {
		if (book.bookcount >= 0) {
			book.printBook();
		}
	}

	// 예매수량줄이기
	private static void bookRemoveCount(ArrayList<Movie> movie) {
		sc.nextLine();
		boolean quit = false;

		while (!quit) {
			System.out.print("수량 줄일 영화제목을 입력: ");
			String scStr = sc.nextLine();

			boolean flag = false;
			int numId = -1;
			for (int i = 0; i < movie.size(); i++) {
				if (scStr.equals(movie.get(i).getName())) {
					numId = i;
					flag = true;
					break;
				}
			}
			// 일치하면 해당 영화의 수량 줄인다.
			if (flag) {
				System.out.println("수량을 줄이시겠습니가? Y|N");
				scStr = sc.nextLine();

				if (scStr.toUpperCase().equals("Y") || scStr.toUpperCase().equals("y")) {
					System.out.println(movie.get(numId).getName() + "수량이 줄었습니다.");
					if (!book.isDeleteMovie(movie.get(numId).getName())) {
						book.insertMovie(movie.get(numId));
					}
				}
				quit = true;
			} else {
				System.out.println("다시 입력해주세요");
			}
		}

	}

	// 예매 리스트 비우기
	private static void bookRemoveItem() throws BookException {
		if (book.bookcount == 0) {
			throw new BookException("예매리스트에 항목이 없습니다.");
		} else {
			movieBookList();
			boolean quit = false;
			while (!quit) {
				sc.nextLine(); // 버퍼

				System.out.print("예매리스트에서 삭제할 영화의 제목을 입력하세요: ");
				String str = sc.nextLine();
				boolean flag = false;
				int numId = -1;

				for (int i = 0; i < book.bookcount; i++) {
					if (str.equals(book.bookItem.get(numId).getMovieName())) {
						numId = i;
						flag = true;
						break;
					}
				}
				if (flag) {
					System.out.println("예매리스트에 항목을 삭제하겠습니까? Y|N");
					str = sc.nextLine();

					if (str.toUpperCase().equals("Y") || str.toUpperCase().equals("y")) {
						System.out.println(book.bookItem.get(numId).getMovieName() + "가 예매리스트에 삭제되었습니다.");
						book.removeBook(numId);
					}
					quit = true;
				} else {
					System.out.println("다시 입력해 주세요.");
				}
			}

		}

	}

	// 영수증 출력
	private static void bookMoviekBill() {
		book.printBill();
	}

	// 종료
	private static void menuExit() {
		System.out.println(" 종료");

	}

	private static void menuAdminLogin() {
		System.out.println("관리자 정보를 입력하세요");

		sc.nextLine(); // 버퍼
		System.out.print("아이디 : ");
		String adminId = sc.nextLine();

		System.out.print("패스워드 : ");
		String adminPW = sc.nextLine();
		Member admin = new Member();

		boolean exit = false;
		if (adminId.equals(admin.getAdminId()) && (adminPW.equals(admin.getAdminPassword()))) {
			while (!exit) {
				try {
					// 관리자 메뉴 호출
					adminMenuInfo();
					System.out.print("항목을 선택해주세요");
					int menu = sc.nextInt();
					if (menu < 1 || menu > 3) {
						System.out.println("제대로 입력해주세요");
					} else {
						switch (menu) {
						case 1:
							// 영화 추가
							addMovie();
							break;
						case 2:
							// 영화삭제
							removeMovie(null);
							break;
						case 3:
							// 종료
							System.out.println("관리자 모드를 종료합니다.");
							exit = true;
							break;
						}

					}

				} catch (Exception e) {
					System.out.println("잘못된 선택입니다.");
					exit = true;
				}
			}
		} else {
			System.out.println("관리자의 정보가 일치하지 않습니다.");
		}
	}

	// 영화삭제
	public static void removeMovie(ArrayList<Movie> movie) {
		ArrayList<Movie> movieInfoList = null;
		movieList(movie);
		boolean quit = false;

		while (!quit) {
			System.out.print("삭제할 영화의 제목을 입력해주세요");
			String title = sc.nextLine();

			boolean flag = false;
			int numId = -1;
			for (int i = 0; i < movie.size(); i++) {
				if (title.equals(movie.get(i).getName())) {
					numId = i;
					flag = true;
					break;
				}
			}
			// 일치하면 해당 영화를 삭제한다.
			if (flag) {
				System.out.println("정말 리스트에서 삭제하시겠습니까? Y|N");
				title = sc.nextLine();

				if (title.toUpperCase().equals("Y") || title.toUpperCase().equals("y")) {
					System.out.println(movie.get(numId).getName() + "를 삭제했습니다.");
					// 삭제코드
					deleteObjectFromFile(movieInfoList);
				}
				quit = true;
			} else
				System.out.println("다시 입력해주세요");
		}
	}

	public static void deleteObjectFromFile(ArrayList<Movie> movie) {
//	      System.out.print("삭제할 영화의 제목을 입력해주세요");
//	      String title = sc.nextLine();
//	      boolean quit = false;
//	      try {
//	         File inputFile = new File(file);
//	         File tempFile = new File("movie.txt");
//	         BufferedReader br = new BufferedReader(new FileReader(inputFile));
//	         BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
//	         String movieName;
//	         String[] readMovie = new String[7];
//	         while((movieName = br.readLine()) != null) {
//	               // 일치하면 해당 영화를 삭제한다.
//	               if (movieName.contains(title)) {
//	                  readMovie[0] = br.readLine();
//	                  readMovie[1] = movieName;
//	                  readMovie[2] = br.readLine();
//	                  readMovie[3] = br.readLine();
//	                  readMovie[4] = br.readLine();
//	                  readMovie[5] = br.readLine();
//	                  readMovie[6] = br.readLine();
//	                  System.out.println("정말 리스트에서 삭제하시겠습니까? Y|N");
//	                  title = sc.nextLine();
		//
//	                  if (title.toUpperCase().equals("Y") || title.toUpperCase().equals("y")) {
//	                     System.out.println(title + "를 삭제했습니다.");
//	                     
//	                  }
//	                  quit = true;
//	               } else
//	                  System.out.println("다시 입력해주세요");
//	            }
//	         }
//	         
//	      }catch(
		//
		// FileNotFoundException e){
//	      e.printStackTrace();
		// }catch(
		// IOException e)
		// {
//	      e.printStackTrace();
		// }
		// }
		System.out.print("삭제할 영화의 제목을 입력해주세요");
		String title = sc.nextLine();
		try {
			FileReader fr = new FileReader("movie.txt");
			BufferedReader br = new BufferedReader(fr);

			String movieId;
			String[] readMovie = new String[7];
			while ((movieId = br.readLine()) != null) {
				if (movieId.contains(title)) {
					readMovie[0] = movieId;
					readMovie[1] = br.readLine();
					readMovie[2] = br.readLine();
					readMovie[3] = br.readLine();
					readMovie[4] = br.readLine();
					readMovie[5] = br.readLine();
					readMovie[6] = br.readLine();
				}
				Movie movieList = new Movie(readMovie[0], readMovie[1], readMovie[2], readMovie[3], readMovie[4],
						readMovie[5], readMovie[6]);
				movie.add(movieList);
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 관리자 메뉴 호출
	public static void adminMenuInfo() {
		System.out.println("****************************************");
		System.out.println("\t\t" + "관리자 모드");

		System.out.println("****************************************");
		System.out.println(" 1. 영화추가 \t2. 영화삭제");
		System.out.println(" 3. 종료");
		System.out.println("****************************************");

	}

	// 관리자 정보가 일치하면 영화를 추가하는 메서드
	public static void addMovie() {
		sc.nextLine();// 버퍼
		String[] writeMovie = new String[7];
		System.out.println("영화 정보를 추가하겠습니까?   Y  |  N  ");
		String str = sc.nextLine();

		if (str.toUpperCase().equals("Y")) {

			Date date = new Date(0);

			SimpleDateFormat formatter = new SimpleDateFormat("yyMMddhhmmss");
			String strDate = formatter.format(date);
			writeMovie[0] = "movie" + strDate;
			System.out.println("영화 ID : " + writeMovie[0]);

			System.out.print("영화제목 : ");
			writeMovie[1] = sc.nextLine();
			System.out.print("상영 시간(예:2024/01/01 hh:mm) : ");
			writeMovie[2] = sc.nextLine();
			System.out.print("감독 : ");
			writeMovie[3] = sc.nextLine();
			System.out.print("장르 : ");
			writeMovie[4] = sc.nextLine();
			System.out.print("개봉일(예:2024/01/01) : ");
			writeMovie[5] = sc.nextLine();
			System.out.print("관 번호 : ");
			writeMovie[6] = sc.nextLine();

			try {
				// 새 영화 정보를 텍스트파일에 추가하기 위해 true
				FileWriter fw = new FileWriter("movie.txt", true);

				for (int i = 0; i < 7; i++) {
					fw.write(writeMovie[i] + "\n");
				}
				fw.close();
				System.out.println("새 영화 정보가 저장되었습니다.");
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("취소하였습니다.");
		}

	}

}
