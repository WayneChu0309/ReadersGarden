package com.stockList.model;

import java.util.List;

public interface StockListDAO_interface {
	void add(StockListVO stockList);
	void update(StockListVO stockList);
	void delete(Integer listId);
	Integer availStock(Integer stockId); // 館藏可借數量 / 扣掉預訂、借出、報廢
	StockListVO findByListId(Integer listId);
	List<StockListVO> getAll();
}
