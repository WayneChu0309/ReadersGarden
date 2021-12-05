package com.vendoract.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

public class VendorActVO implements Serializable {
	
	private Integer vactid;
	private String name;
	private Integer vendorid;
	private Date date;
	private Integer amount;
	private Integer siteid;
	private Integer actid;
	private String progress;
	private Date rtlstart;
	private Date rtlend;
	private Date actstart;
	private Date actend;
	private Date tkstart;
	private Date tkend;
	private Integer price;
	private String actcnt;
	private byte[] img;
	private String note;

	@Override
	public String toString() {
		return "VendorActVO [vactid=" + vactid + ", name=" + name + ", vendorid=" + vendorid + ", date=" + date
				+ ", amount=" + amount + ", siteid=" + siteid + ", actid=" + actid + ", progress=" + progress
				+ ", rtlstart=" + rtlstart + ", rtlend=" + rtlend + ", actstart=" + actstart + ", actend=" + actend
				+ ", tkstart=" + tkstart + ", tkend=" + tkend + ", price=" + price + ", actcnt=" + actcnt + "]";
	}

	public VendorActVO() {
		
	}

	public VendorActVO(Integer vactid, String name, Integer vendorid, Date date, Integer amount, Integer siteid,
			Integer actid, String progress, Date rtlstart, Date rtlend, Date actstart, Date actend, Date tkstart,
			Date tkend, Integer price, String actcnt, byte[] img) {
		super();
		this.vactid = vactid;
		this.name = name;
		this.vendorid = vendorid;
		this.date = date;
		this.amount = amount;
		this.siteid = siteid;
		this.actid = actid;
		this.progress = progress;
		this.rtlstart = rtlstart;
		this.rtlend = rtlend;
		this.actstart = actstart;
		this.actend = actend;
		this.tkstart = tkstart;
		this.tkend = tkend;
		this.price = price;
		this.actcnt = actcnt;
		this.img = img;
	}

	public Integer getVactid() {
		return vactid;
	}

	public void setVactid(Integer vactid) {
		this.vactid = vactid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVendorid() {
		return vendorid;
	}

	public void setVendorid(Integer vendorid) {
		this.vendorid = vendorid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getSiteid() {
		return siteid;
	}

	public void setSiteid(Integer siteid) {
		this.siteid = siteid;
	}

	public Integer getActid() {
		return actid;
	}

	public void setActid(Integer actid) {
		this.actid = actid;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public Date getRtlstart() {
		return rtlstart;
	}

	public void setRtlstart(Date rtlstart) {
		this.rtlstart = rtlstart;
	}

	public Date getRtlend() {
		return rtlend;
	}

	public void setRtlend(Date rtlend) {
		this.rtlend = rtlend;
	}

	public Date getActstart() {
		return actstart;
	}

	public void setActstart(Date actstart) {
		this.actstart = actstart;
	}

	public Date getActend() {
		return actend;
	}

	public void setActend(Date actend) {
		this.actend = actend;
	}

	public Date getTkstart() {
		return tkstart;
	}

	public void setTkstart(Date tkstart) {
		this.tkstart = tkstart;
	}

	public Date getTkend() {
		return tkend;
	}

	public void setTkend(Date tkend) {
		this.tkend = tkend;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getActcnt() {
		return actcnt;
	}

	public void setActcnt(String actcnt) {
		this.actcnt = actcnt;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
}
