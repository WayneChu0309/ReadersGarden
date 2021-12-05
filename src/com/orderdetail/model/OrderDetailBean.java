package com.orderdetail.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.google.gson.annotations.Expose;

@Entity
@DynamicInsert
@Table(name = "ORDERDETAIL")
public class OrderDetailBean {
	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DETAILID")
	private Integer detailId;
	private Integer orderId;
	private Integer vactId;
	private java.sql.Date startdate;
	private java.sql.Date stopdate;
	private byte[] qrcode;
	private java.sql.Date enterDate;
	private Integer detailStatus;
	
	
	public Integer getDetailId() {
		return detailId;
	}
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getVactId() {
		return vactId;
	}
	public void setVactId(Integer vactId) {
		this.vactId = vactId;
	}
	public java.sql.Date getStartdate() {
		return startdate;
	}
	public void setStartdate(java.sql.Date startdate) {
		this.startdate = startdate;
	}
	public java.sql.Date getStopdate() {
		return stopdate;
	}
	public void setStopdate(java.sql.Date stopdate) {
		this.stopdate = stopdate;
	}
	public byte[] getQrcode() {
		return qrcode;
	}
	public void setQrcode(byte[] qrcode) {
		this.qrcode = qrcode;
	}
	public java.sql.Date getEnterDate() {
		return enterDate;
	}
	public void setEnterDate(java.sql.Date enterDate) {
		this.enterDate = enterDate;
	}
	public Integer getDetailStatus() {
		return detailStatus;
	}
	public void setDetailStatus(Integer detailStatus) {
		this.detailStatus = detailStatus;
	}
	@Override
	public String toString() {
		return "OrderDetailBean [detailId=" + detailId + ", orderId=" + orderId + ", vactId=" + vactId + ", startdate="
				+ startdate + ", stopdate=" + stopdate + ", enterDate=" + enterDate + ", detailStatus=" + detailStatus
				+ "]";
	}
	
	
	
}
