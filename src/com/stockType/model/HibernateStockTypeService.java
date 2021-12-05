package com.stockType.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.HibernateUtil;

public class HibernateStockTypeService {
	
	private StockTypeDAO dao;
	
	public HibernateStockTypeService(StockTypeDAO stockTypeDAO) {
		this.dao = stockTypeDAO;
	}
	
	public StockTypeBean add(StockTypeBean stockType) {
		return dao.add(stockType);
	} 
	
	public StockTypeBean findByTypeId(Integer typeId) {
		return dao.findByTypeId(typeId);
	}
	
	public List<StockTypeBean> getBook() {
		return dao.getBook();
	}
	
	public List<StockTypeBean> getMovie() {
		return dao.getMovie();
	}
	
	public List<StockTypeBean> getAll() {
		return dao.getAll();
	}
	

}
