package com.vendor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VENDOR")
public class VendorBean implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VENDORID")
	private Integer vendorid;
	
	@Column(name = "COMPANY")
	private String company;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "TAXID")
	private Integer taxid;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "TEL")
	private String tel;
	
	@Column(name = "MOBILE")
	private String mobile;
	
	@Column(name = "ADDR")
	private String addr;
	
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

	@Override
	public String toString() {
		return "VendorBean [vendorid=" + vendorid + ", company=" + company + ", status=" + status + ", taxid=" + taxid
				+ ", name=" + name + ", email=" + email + ", password=" + password + ", tel=" + tel + ", mobile="
				+ mobile + ", addr=" + addr + "]";
	}
}



