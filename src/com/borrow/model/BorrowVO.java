package com.borrow.model;

import java.io.Serializable;
import java.sql.Date;
//implements Serializable
public class BorrowVO implements Serializable{
	private Integer borrowId;
	private Integer memberId;
	private Integer stockId; // 館藏項目ID
 	private Integer listId;  // 館藏ID
	private Date preBoDate; // 預訂借閱日
	private Date actBoDate; // 實際借閱日
	private Date preReDate; // 預計歸還日 / 預訂日+30天
	private Date actReDate; // 實際歸還日
	private String scoreContent;
	private Date scoreDate;
	private Integer score;
	private Integer boStates; // 1.預訂 2.借閱 3.歸還 4.逾期未還 5.逾期歸還
	
	public BorrowVO() {}

	public BorrowVO(Integer borrowId, Integer memberId, Integer stockId, Integer listId, Date preBoDate, Date actBoDate,
			Date preReDate, Date actReDate, String scoreContent, Date scoreDate, Integer score, Integer boStates) {
		super();
		this.borrowId = borrowId;
		this.memberId = memberId;
		this.stockId = stockId;
		this.listId = listId;
		this.preBoDate = preBoDate;
		this.actBoDate = actBoDate;
		this.preReDate = preReDate;
		this.actReDate = actReDate;
		this.scoreContent = scoreContent;
		this.scoreDate = scoreDate;
		this.score = score;
		this.boStates = boStates;
	}

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

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
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
		return "BorrowVO [borrowId=" + borrowId + ", memberId=" + memberId + ", stockId=" + stockId + ", listId="
				+ listId + ", preBoDate=" + preBoDate + ", actBoDate=" + actBoDate + ", preReDate=" + preReDate
				+ ", actReDate=" + actReDate + ", scoreContent=" + scoreContent + ", scoreDate=" + scoreDate
				+ ", score=" + score + ", boStates=" + boStates + "]";
	}
	
}

