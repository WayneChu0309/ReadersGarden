package com.site.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

public class SiteVO implements Serializable{

	private Integer siteid;
	private String name;
	private Integer area;
	private Integer daycost;
	private Integer capacity;
	private byte[] img;
	
	public SiteVO() {
		
	}
	
	public SiteVO(Integer siteid, String name, Integer area, Integer daycost, Integer capacity, byte[] img) {
		this.siteid = siteid;
		this.name = name;
		this.area= area;
		this.daycost = daycost;
		this.capacity = capacity;
		this.img = img;
	}

	

	@Override
	public String toString() {
		return "SiteVO [siteid=" + siteid + ", name=" + name + ", area=" + area + ", daycost=" + daycost + ", capacity="
				+ capacity + "]";
	}

	public Integer getSiteid() {
		return siteid;
	}

	public void setSiteid(Integer siteid) {
		this.siteid = siteid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getDaycost() {
		return daycost;
	}

	public void setDaycost(Integer daycost) {
		this.daycost = daycost;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}
	
	public void setImg(String path) {
		
		FileInputStream fis = null;
		byte[] buffer = null;
		
		try {
			fis = new FileInputStream(path);
			buffer = new byte[fis.available()];
			fis.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		this.img = buffer;	
	}
}