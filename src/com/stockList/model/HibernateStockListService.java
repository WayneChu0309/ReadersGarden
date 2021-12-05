package com.stockList.model;

import java.util.List;

public class HibernateStockListService {
	private StockListDAO dao;
	
	public HibernateStockListService(StockListDAO dao) {
		this.dao = dao;
	}
	
	public StockListBean add(StockListBean listBean) {
		return dao.add(listBean);
	}
	
	public StockListBean update(StockListBean listBean) {
		return dao.update(listBean);
	}
	
	public int availStock(Integer stockId) {
		return dao.availStock(stockId);
	}
	
	public StockListBean findByListId(Integer listId) {
		return dao.findByListId(listId);
	}
	
	public List<StockListBean> findByAvailList(Integer stockId){
		return dao.findByAvailList(stockId);
	}
	
	public int findByAvailListId(Integer stockId) {
		return dao.findByAvailListId(stockId);
	}
	
	public List<StockListBean> findByAllList(Integer stockId) {
		return dao.findByAllList(stockId);
	}
}
