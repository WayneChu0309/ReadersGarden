package com.borrow.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.stock.model.StockBean;

@Entity
@Table(name = "BORROW")
public class BorrowBean implements Serializable{
	@Id
	@Expose
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BORROWID")
	private Integer borrowId;
	private Integer memberId;
	@Expose
	@ManyToOne()
	@JoinColumn(
			name = "STOCKID",
			referencedColumnName = "STOCKID"
	)
	private StockBean stockBean; // 館藏項目ID
	@Expose
 	private Integer listId;  // 館藏ID
	@Expose
	private Date preBoDate; // 預訂借閱日
	@Expose
	private Date actBoDate; // 實際借閱日
	@Expose
	private Date preReDate; // 預計歸還日 / 預訂日加一個月
	@Expose
	private Date actReDate; // 實際歸還日
	@Expose
	private String scoreContent;
	@Expose
	private Date scoreDate;
	@Expose
	private Integer score;
	@Expose
	private Integer boStates; // 1.預訂 2.借閱 3.逾期未還 4.歸還 5.逾期歸還  6.取消  7. 確認取消 8.確認預訂
	
	public Integer getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public StockBean getStockBean() {
		return stockBean;
	}
	public void setStockBean(StockBean stockBean) {
		this.stockBean = stockBean;
	}
	public Integer getListId() {
		return listId;
	}
	public void setListId(Integer listId) {
		this.listId = listId;
	}
	public Date getPreBoDate() {
		return preBoDate;
	}
	public void setPreBoDate(Date preBoDate) {
		this.preBoDate = preBoDate;
	}
	public Date getActBoDate() {
		return actBoDate;
	}
	public void setActBoDate(Date actBoDate) {
		this.actBoDate = actBoDate;
	}
	public Date getPreReDate() {
		return preReDate;
	}
	public void setPreReDate(Date preReDate) {
		this.preReDate = preReDate;
	}
	public Date getActReDate() {
		return actReDate;
	}
	public void setActReDate(Date actReDate) {
		this.actReDate = actReDate;
	}
	public String getScoreContent() {
		return scoreContent;
	}
	public void setScoreContent(String scoreContent) {
		this.scoreContent = scoreContent;
	}
	public Date getScoreDate() {
		return scoreDate;
	}
	public void setScoreDate(Date scoreDate) {
		this.scoreDate = scoreDate;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getBoStates() {
		return boStates;
	}
	public void setBoStates(Integer boStates) {
		this.boStates = boStates;
	}
	@Override
	public String toString() {
		return "BorrowBean [borrowId=" + borrowId + ", memberId=" + memberId + ", listId=" + listId + ", preBoDate="
				+ preBoDate + ", actBoDate=" + actBoDate + ", preReDate=" + preReDate + ", actReDate=" + actReDate
				+ ", scoreContent=" + scoreContent + ", scoreDate=" + scoreDate + ", score=" + score + ", boStates="
				+ boStates + "]";
	}
	
}
