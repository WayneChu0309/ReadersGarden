package com.library.model;
import java.io.Serializable;

public class LibraryVO implements Serializable {
	private Integer libId;     // libID (流水號)
	private String libName;
	private String libLoc;
	private String libTime;
	private String libTel;
	private String libEmail;
	
	public LibraryVO() {}
	
	public LibraryVO(String libName, String libLoc, String libTime, String libTel,
			String libEmail) {
		super();
		this.libName = libName;
		this.libLoc = libLoc;
		this.libTime = libTime;
		this.libTel = libTel;
		this.libEmail = libEmail;
	}
	
	public Integer getLibraryId() {
		return libId;
	}
	public void setLibraryId(Integer libId) {
		this.libId = libId;
	}
	public String getLibraryName() {
		return libName;
	}
	public void setLibraryName(String libName) {
		this.libName = libName;
	}
	public String getLibraryAddress() {
		return libLoc;
	}
	public void setLibraryAddress(String libLoc) {
		this.libLoc = libLoc;
	}
	public String getLibraryTime() {
		return libTime;
	}
	public void setLibraryTime(String libTime) {
		this.libTime = libTime;
	}
	public String getLibraryTel() {
		return libTel;
	}
	public void setLibraryTel(String libTel) {
		this.libTel = libTel;
	}
	public String getLibraryEmail() {
		return libEmail;
	}
	public void setLibraryEmail(String libEmail) {
		this.libEmail = libEmail;
	}
	
	
}
