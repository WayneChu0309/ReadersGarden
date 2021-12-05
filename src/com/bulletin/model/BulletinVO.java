package com.bulletin.model;

import java.io.Serializable;
import java.sql.Date;

public class BulletinVO implements Serializable{
	private Integer bulletinId;		// 公告ID (流水號)
	private Integer memberId;       // 管理員ID
	private String buContent; // 公告內容
	private Date buDate; 		// YYYY-MM-DD
	
	public BulletinVO() {}
	
	public BulletinVO(Integer bulletinId, Integer memberId, String buContent, Date buDate) {
		super();
		this.bulletinId = bulletinId;
		this.memberId = memberId;
		this.buContent = buContent;
		this.buDate = buDate;
	}
	
	public Integer getBulletinId() {
		return bulletinId;
	}
	public void setBulletinId(Integer bulletinId) {
		this.bulletinId = bulletinId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getBulletinContent() {
		return buContent;
	}
	public void setBulletinContent(String buContent) {
		this.buContent = buContent;
	}
	public Date getBulletinDate() {
		return buDate;
	}
	public void setBulletinDate(Date buDate) {
		this.buDate = buDate;
	}
}
