package com.stock.model;

import java.util.List;

public class NewStockService {
	private NewStockDAO dao;
	public NewStockService(NewStockDAO dao) {
		this.dao = dao;
	}
	
	public List<NewStock> findNew(){
		return dao.findNew();
	}
	public void update(NewStock newstock) {
		dao.update(newstock);
	}
	
	public NewStock findByPk(Integer newId) {
		return dao.findByPk(newId);
	}
}
