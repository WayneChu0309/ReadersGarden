package com.stock.model;

import java.util.List;

import com.stockType.model.StockTypeBean;

public interface StockDAO {
	public abstract StockBean add(StockBean stockBean);
	public abstract StockBean update(StockBean stockBean);
	public abstract boolean delete(Integer stockId);
	public abstract StockBean findByStockId(Integer stockId); 
	public abstract List<StockBean> getKeyword(String keyword);
	public abstract List<StockBean> getAll(Integer typeId);
	// 分頁
	public abstract List<StockBean> getPage(Integer typeId, Integer pageNumber); 
	// 該類總數, 用於計算分幾頁用
	public abstract int getTypeIdTotal(Integer typeId);
	// 書籍 最新一筆, 給slide用
	public abstract StockBean getNewBook();
	// 電影 最新一筆, 給slide用
	public abstract StockBean getNewMovie();
}
