package com.stockType.model;

import java.util.List;

public interface StockTypeDAO_interface {
	void add(StockTypeVO stockType);
	void update(StockTypeVO stockType);
	void delete(Integer typeId);
	StockTypeVO findByTypeId(Integer typeId);
	List<StockTypeVO> getAll();
	List<StockTypeVO> getBook();
	List<StockTypeVO> getMovie();
}
