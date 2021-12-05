package com.stock.model;

import java.util.List;

public interface StockDAO_interface {
	Integer add(StockVO stock);
	void update(StockVO stock);
	void delete(Integer stockId);
	Integer getLast(Integer stockId); // 剩餘數量
	StockVO findByStockId(Integer stockId); 
	Integer findByTypeId(Integer typeId); // 種類 總數
	List<StockVO> getKeyword(String keyword);
	List<StockVO> getAll(Integer typeId);
	List<StockVO> getPage(Integer typeId, Integer pageNumber); // 沒用到
	List<StockVO> getPageInf(Integer typeId, Integer pageNumber); // 沒用到
}
