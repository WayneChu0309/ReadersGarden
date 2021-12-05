package com.member.model;

import java.io.Serializable;
import java.sql.Date;

public class MemberVO implements Serializable {
	private Integer number;
	private String email;
	private String name;
	private Date birthday;
	private String phoneNumber;
	private String address;
	private String ID;
	private String password;
	private String status;
	
	
	public MemberVO() {
	}
	
	public MemberVO(Integer number, String email, String name, Date birthday, String phoneNumber, String address, String ID, String password, String status) {
		this.number = number;
		this.email = email;
		this.name = name;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.ID = ID;
		this.password = password;
		this.status = status;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void User(int number, String email, String password) {
		
		this.number = number;
		this.email = email;
		this.password = password;
	}
	public void User(String email, String password) {
		
		this.email = email;
		this.password = password;
	}
	public void User(){
		
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	
}