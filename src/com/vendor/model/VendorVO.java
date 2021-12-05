package com.vendor.model;

import java.io.Serializable;

public class VendorVO implements Serializable{

	private Integer vendorid;
	private String company;
	private String status;
	private Integer taxid;
	private String name;
	private String email;
	private String password;
	private String tel;
	private String mobile;
	private String addr;

	public VendorVO() {

	}
	
	public String toString() {
		return "VendorVO [vendorid=" + vendorid + ", company=" + company + ", status=" + status + ", taxid=" + taxid
				+ ", name=" + name + ", email=" + email + ", password=" + password + ", tel=" + tel + ", mobile="
				+ mobile + ", addr=" + addr + "]";
	}

	public VendorVO(Integer vendorid, String company, Integer taxid, String name, String email, String password, String tel, String mobile, String addr) {
		this.vendorid = vendorid;
		this.company = company;
		this.taxid = taxid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.tel = tel;
		this.mobile = mobile;
		this.addr = addr;
	}
	
	public Integer getVendorid() {
		return vendorid;
	}

	public void setVendorid(Integer vendorid) {
		this.vendorid = vendorid;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getTaxid() {
		return taxid;
	}

	public void setTaxid(Integer taxid) {
		this.taxid = taxid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}


