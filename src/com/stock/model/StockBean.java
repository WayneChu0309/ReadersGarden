package com.stock.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.stockType.model.StockTypeBean;

@Entity
@Table(name = "STOCK")
public class StockBean implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STOCKID")
	// @Expose gson to json 
	@Expose
	private Integer stockId;  // 流水號
	@ManyToOne(
			cascade = {CascadeType.PERSIST}
	)
	@JoinColumn(
			name = "TYPEID",
			referencedColumnName = "TYPEID"
	)
	private StockTypeBean typeBean;
	@Expose
	@Column(name = "STOCKNAME")
	private String stockName;
	@Expose
	@Column(name = "AUTHOR")
	private String author;
	@Expose
	@Column(name = "PRESS")
	private String press;
	@Expose
	@Column(name = "ISSUEDDATE")
	private java.sql.Date issuedDate;
	@Expose
	@Column(name = "STOCKCONTENT")
	private String stockContent;
	@Expose
	@Column(name = "STOCKSCORE")
	private double stockScore;
	@Column(name = "STOCKIMG")
	private byte[] stockImg;
	
	
	public Integer getStockId() {
		return stockId;
	}
	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}
	
	public StockTypeBean getTypeBean() {
		return typeBean;
	}
	public void setTypeBean(StockTypeBean typeBean) {
		this.typeBean = typeBean;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public java.sql.Date getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(java.sql.Date issuedDate) {
		this.issuedDate = issuedDate;
	}
	public String getStockContent() {
		return stockContent;
	}
	public void setStockContent(String stockContent) {
		this.stockContent = stockContent;
	}
	public double getStockScore() {
		return stockScore;
	}
	public void setStockScore(double stockScore) {
		this.stockScore = stockScore;
	}
	public byte[] getStockImg() {
		return stockImg;
	}
	public void setStockImg(byte[] stockImg) {
		this.stockImg = stockImg;
	}
	
	@Override
	public String toString() {
		return "StockBean [stockId=" + stockId + ", stockName=" + stockName + ", author=" + author + ", press=" + press
				+ ", issuedDate=" + issuedDate + ", stockContent=" + stockContent + ", stockScore=" + stockScore
				+ ", stockImg=" + Arrays.toString(stockImg) + "]";
	}
	
}
