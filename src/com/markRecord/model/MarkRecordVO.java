package com.markRecord.model;

import java.io.Serializable;


public class MarkRecordVO implements Serializable {
	private Integer serialNumber;
	private Integer number;
	private Integer stockID;
	private Integer vacID;
	
	public MarkRecordVO() {
		
		
	}
	
	public MarkRecordVO(Integer serialNumber, Integer number, Integer stockID, Integer vacID) {
		
		this.serialNumber = serialNumber;
		this.number = number;
		this.stockID = stockID;
		this.vacID = vacID;
	}
	

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getStockID() {
		return stockID;
	}

	public void setStockID(Integer stockID) {
		this.stockID = stockID;
	}

	public Integer getVacID() {
		return vacID;
	}

	public void setVacID(Integer vacID) {
		this.vacID = vacID;
	}


	
	
	
	
}