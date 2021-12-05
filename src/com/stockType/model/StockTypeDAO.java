package com.stockType.model;

import java.util.List;
import java.util.Set;

import com.stock.model.StockBean;

public interface StockTypeDAO {
	// 新增
	public abstract StockTypeBean add(StockTypeBean stockType);
	// Id 查詢
	public abstract StockTypeBean findByTypeId(Integer typeId);
	// 種類為 書籍 的類別
	public abstract List<StockTypeBean> getBook();
	// 種類為 電影 的類別
	public abstract List<StockTypeBean> getMovie();
	// 全部的種類
	public abstract List<StockTypeBean> getAll();
	public abstract List<StockBean> findByTypeIdTotal(Integer typeId);
}
