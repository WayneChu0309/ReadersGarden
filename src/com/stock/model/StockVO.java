package com.stock.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class StockVO implements Serializable {
	private Integer stockId;  // 流水號
	private Integer typeId;
	private String stockName;
	private String author;
	private String press;
	private Date issuedDate;
	private String stockContent;
	private double stockScore;
	private byte[] stockImg;
	
	public StockVO() {}
	
	public StockVO(Integer stockId, Integer typeId, String stockName, String author, String press,
			Date issuedDate, String stockContent, double stockScore, byte[] stockImg) {
		super();
		this.stockId = stockId;
		this.typeId = typeId;
		this.stockName = stockName;
		this.author = author;
		this.press = press;
		this.issuedDate = issuedDate;
		this.stockContent = stockContent;
		this.stockScore = stockScore;
		this.stockImg = stockImg;
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
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

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
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
		return "StockVO [stockId=" + stockId + ", typeId=" + typeId + ", stockName=" + stockName + ", author=" + author
				+ ", press=" + press + ", issuedDate=" + issuedDate + ", stockContent=" + stockContent + ", stockScore="
				+ stockScore + ", stockImg=" + Arrays.toString(stockImg) + "]";
	}
	
}
