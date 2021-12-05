package com.order.model;

import java.io.Serializable;

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
@Table(name = "ORDERITEM")
public class OrderBean implements Serializable{
	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDERID")
	private Integer orderId;
	private Integer vactId;
	private Integer memberId;
	private Integer ordercount; // 數量
	private Integer total; // 總價
	private java.sql.Timestamp ordertime;
	private Integer orderStatus; // 1.待付款 2.付款 3.取消
	
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
	
	public Integer getMemberId() {
		return memberId;
	}
	
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
	public Integer getOrdercount() {
		return ordercount;
	}
	
	public void setOrdercount(Integer ordercount) {
		this.ordercount = ordercount;
	}
	
	public Integer getTotal() {
		return total;
	}
	
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public java.sql.Timestamp getOrdertime() {
		return ordertime;
	}
	
	public void setOrdertime(java.sql.Timestamp ordertime) {
		this.ordertime = ordertime;
	}
	
	public Integer getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	@Override
	public String toString() {
		return "OrderBean [orderId=" + orderId + ", vactId=" + vactId + ", memberId=" + memberId + ", ordercount="
				+ ordercount + ", total=" + total + ", ordertime=" + ordertime + ", orderStatus=" + orderStatus + "]";
	}
	
	
}
