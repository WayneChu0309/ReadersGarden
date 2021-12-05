package com.stock.model;

import java.util.List;

public class HibernateStockService {
	private StockDAO dao;
	
	public HibernateStockService(StockDAO dao) {
		this.dao = dao;
	}
	
	public StockBean add(StockBean stockBean) {
		return dao.add(stockBean);
	}
	
	public StockBean update(StockBean stockBean) {
		return dao.update(stockBean);
	} 
	
	public StockBean findByStockId(Integer stockId) {
		return dao.findByStockId(stockId);
	}
	
	public List<StockBean> getKeyword(String keyword){
		return dao.getKeyword(keyword);
	}
	
	public List<StockBean> getAll(Integer typeId){
		return dao.getAll(typeId);
	}
	
	public List<StockBean> getPage(Integer typeId, Integer pageNumber){
		return dao.getPage(typeId, pageNumber);
	}
	
	public int getTypeIdTotal(Integer typeId) {
		return dao.getTypeIdTotal(typeId);
	}
	
	public StockBean getNewBook() {
		return dao.getNewBook();
	}
	
	public StockBean getNewMovie() {
		return dao.getNewMovie();
	}
	
}
