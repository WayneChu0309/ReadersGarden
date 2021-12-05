package com.stockList.model;

import java.util.List;

public class StockListService {
	
	private StockListDAO_interface dao;
	
	public StockListService() {
		dao = new StockListJNDIDAO();
	}
	
	public StockListVO addStockList(Integer stockId, Integer listStates) {
		StockListVO stockListVO = new StockListVO();
		stockListVO.setStockId(stockId);
		stockListVO.setListStates(listStates);
		dao.add(stockListVO);
		return stockListVO;
	}
	
	public StockListVO updateStockList(Integer listId, Integer stockId, Integer listStates) {
		StockListVO stockListVO = new StockListVO();
		stockListVO.setListId(listId);
		stockListVO.setStockId(stockId);
		stockListVO.setListStates(listStates);
		dao.add(stockListVO);
		return stockListVO;
	}
	
	public void deleteStockList(Integer listId) {
		dao.delete(listId);
	}
	
	public StockListVO getOneStockList(Integer listId) {
		return dao.findByListId(listId);
	}
	
	public List<StockListVO> getAll() {
		return dao.getAll();
	}
	
	public Integer getAvail(Integer listId) {
		return dao.availStock(listId);
	}
}
