package com.stockList.model;

import java.io.Serializable;

public class StockListVO implements Serializable{
	private Integer listId;
	private Integer stockId;
	private Integer listStates; // 1.可借閱 2.借出 3.報廢  3 choice 1
	
	public StockListVO() {}

	public StockListVO(Integer listId, Integer stockID, Integer listStates) {
		super();
		this.listId = listId;
		this.stockId = stockID;
		this.listStates = listStates;
	}

	public Integer getListId() {
		return listId;
	}

	public void setListId(Integer listId) {
		this.listId = listId;
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockID) {
		this.stockId = stockID;
	}

	public Integer getListStates() {
		return listStates;
	}

	public void setListStates(Integer listStates) {
		this.listStates = listStates;
	}
	
}
