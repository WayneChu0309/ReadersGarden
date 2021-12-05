package com.stockList.model;

import java.io.Serializable;

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
@Table(name = "STOCK_LIST")
public class StockListBean implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LISTID")
	@Expose
	private Integer listId;
	
	@ManyToOne()
	@JoinColumn(
			name = "STOCKID",
			referencedColumnName = "STOCKID"
	)
	private StockBean stockBean;
	@Column(name = "LISTSTATES")
	@Expose
	private Integer listStates; // 1.可借閱 2.借出 3.報廢  3 choice 1
	
	public Integer getListId() {
		return listId;
	}
	public void setListId(Integer listId) {
		this.listId = listId;
	}
	public StockBean getStockBean() {
		return stockBean;
	}
	public void setStockBean(StockBean stockBean) {
		this.stockBean = stockBean;
	}
	public Integer getListStates() {
		return listStates;
	}
	public void setListStates(Integer listStates) {
		this.listStates = listStates;
	}
	@Override
	public String toString() {
		return "StockListBean [listId=" + listId + ", listStates=" + listStates + "]";
	}
	
}
