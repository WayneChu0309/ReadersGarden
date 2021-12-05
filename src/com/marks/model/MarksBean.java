package com.marks.model;

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
@Table(name = "MARKS")
@DynamicInsert
public class MarksBean implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MARKSID")
	@Expose
	private Integer marksId;
	private Integer memberId;
	private Integer stockId;
	
	@Column(name = "MARK_STATUS")
	private Integer markStatus;
	public Integer getMarksId() {
		return marksId;
	}
	public void setMarksId(Integer marksId) {
		this.marksId = marksId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getStockId() {
		return stockId;
	}
	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}
	public Integer getMarkStatus() {
		return markStatus;
	}
	public void setMarkStatus(Integer markStatus) {
		this.markStatus = markStatus;
	}
	@Override
	public String toString() {
		return "MarksBean [marksId=" + marksId + ", memberId=" + memberId + ", stockId=" + stockId + ", markStatus="
				+ markStatus + "]";
	}
	
	
}
