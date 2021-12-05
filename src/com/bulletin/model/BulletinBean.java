package com.bulletin.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "BULLETIN")
public class BulletinBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BULLETINID")
	private Integer bulletinId;		// 公告ID (流水號)
	private Integer memberId;       // 管理員ID
	@Expose
	private String buContent; // 公告內容
	@Expose
	private Date buDate; 		// YYYY-MM-DD
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
	public String getBuContent() {
		return buContent;
	}
	public void setBuContent(String buContent) {
		this.buContent = buContent;
	}
	public Date getBuDate() {
		return buDate;
	}
	public void setBuDate(Date buDate) {
		this.buDate = buDate;
	}
	@Override
	public String toString() {
		return "BulletinBean [bulletinId=" + bulletinId + ", memberId=" + memberId + ", buContent=" + buContent
				+ ", buDate=" + buDate + "]";
	}
	
}
