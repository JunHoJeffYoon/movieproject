package com.movie.member;

public class Member {
	private String name; //예매자 이름
	private int phone; 	//예매자 폰번호
	private String adminId = "admin"; //관계자 아이디
	private String AdminPassword = "admin";	//관계자비밀번호
	public Member() {
		super();
	}
	public Member(String name, int phone) {
		super();
		this.name = name;
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public int getPhone() {
		return phone;
	}
	public String getAdminId() {
		return adminId;
	}
	public String getAdminPassword() {
		return AdminPassword;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	

}
